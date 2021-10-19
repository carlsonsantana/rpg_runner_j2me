package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.AbstractCharacterCreatorTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.ActionList;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;

public abstract class AbstractCharacterCreatorFactoryTest
    extends AbstractCharacterCreatorTest {
    private static final int NUMBER_OF_EVENTS_INDEX = 4;
    private static final int DIRECTION_INDEX = 2;
    private static final int EVENT_ID_INDEX = 1;
    private static final int ADDITIONAL_BYTES = 6;

    public void testPassSameDirectionToMapEvent() throws IOException {
        byte direction = RandomGenerator.getRandomDirection();
        MapControllerSpy mapController = new MapControllerSpy();
        byte[] byteArray = generateByteArray("", 0, 0, 1, direction, 1);
        byteArray[byteArray.length - NUMBER_OF_EVENTS_INDEX] = 1;
        byteArray[byteArray.length - 1] = 0;

        InputStream inputStream = generateInputStream(byteArray);

        CharacterCreator characterCreator = (CharacterCreator) createAction(
            mapController,
            inputStream
        );

        characterCreator.execute();

        CharacterElement characterElement = (
            mapController.getLastCharacterElementAdded()
        );
        GameCharacter character = characterElement.getCharacter();
        Action action = character.getInteractiveAction(direction);

        Assert.assertTrue(action instanceof ActionList);
    }

    protected CharacterCreator createCharacterCreator(
        final MapController mapController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        InputStream inputStream = getInputStream(
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );

        try {
            CharacterCreator characterCreator = (CharacterCreator) createAction(
                mapController,
                inputStream
            );

            return characterCreator;
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream getInputStream(
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        byte[] byteArray = generateByteArray(
            characterFileName,
            initialMapPositionX,
            initialMapPositionY,
            0,
            RandomGenerator.getRandomDirection(),
            0
        );

        return generateInputStream(byteArray);
    }

    private byte[] generateByteArray(
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final int actionId,
        final byte direction,
        final int extraBytes
    ) {
        int stringLength = InputStreamHelper.getStringLength(characterFileName);
        int arrayNormalLength = stringLength + ADDITIONAL_BYTES;
        int arrayLength = arrayNormalLength + extraBytes;
        int directionIndex = arrayNormalLength - DIRECTION_INDEX;
        int actionIndex = arrayNormalLength - EVENT_ID_INDEX;
        byte[] byteArray = new byte[arrayLength];

        InputStreamHelper.setByteArray(byteArray, characterFileName);
        InputStreamHelper.setPosition(
            byteArray,
            (stringLength + 1),
            initialMapPositionX,
            initialMapPositionY
        );
        byteArray[directionIndex] = direction;
        byteArray[actionIndex] = (byte) actionId;

        return byteArray;
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        MapController mapController,
        InputStream inputStream
    ) throws IOException;
}

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
    private static final int ADDITIONAL_BYTES = 5;

    public void testPassSameDirectionToMapEvent() throws IOException {
        byte direction = RandomGenerator.getRandomDirection();
        MapControllerSpy mapController = new MapControllerSpy();
        byte[] byteArray = new byte[ADDITIONAL_BYTES + 1];
        InputStreamHelper.setByteArray(byteArray, "");
        InputStreamHelper.setPosition(byteArray, 1, 0, 0);
        byteArray[ADDITIONAL_BYTES - 2] = direction;
        byteArray[ADDITIONAL_BYTES - 1] = 1;
        byteArray[ADDITIONAL_BYTES] = 0;

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
        int stringLength = InputStreamHelper.getStringLength(characterFileName);
        int arrayLength = stringLength + ADDITIONAL_BYTES;
        byte[] byteArray = new byte[arrayLength];
        InputStreamHelper.setByteArray(byteArray, characterFileName);
        InputStreamHelper.setPosition(
            byteArray,
            (stringLength + 1),
            initialMapPositionX,
            initialMapPositionY
        );
        byteArray[arrayLength - 2] = RandomGenerator.getRandomDirection();
        byteArray[arrayLength - 1] = 0;

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        MapController mapController,
        InputStream inputStream
    ) throws IOException;
}

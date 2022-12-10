package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.rpgrunner.Direction;
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
    private static final int NORMAL_BYTES = 6;
    private static final int NUMBER_OF_EVENTS_INDEX = 3;
    private static final byte ALL_DIRECTIONS = (
        Direction.UP | Direction.RIGHT | Direction.DOWN | Direction.LEFT
    );

    public void testPassSameDirectionToMapEvent() throws IOException {
        byte direction = RandomGenerator.getRandomDirection();
        MapControllerSpy mapController = new MapControllerSpy();
        byte[] byteArray = generateByteArray((byte) 0, 0, 0, 1, direction, 1);
        byteArray[byteArray.length - 2] = 1;
        byteArray[byteArray.length - 1] = 0;

        InputStream inputStream = generateInputStream(byteArray);

        CharacterCreator characterCreator = (CharacterCreator) createAction(
            mapController,
            inputStream
        );

        characterCreator.execute();

        GameCharacter character = mapController.getLastCharacterAdded();
        Action action = character.getInteractiveAction(direction);

        Assert.assertTrue(action instanceof ActionList);
    }

    protected CharacterCreator createCharacterCreator(
        final MapController mapController,
        final byte characterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        InputStream inputStream = getInputStream(
            characterIDSprite,
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
        final byte characterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        byte[] byteArray = generateByteArray(
            characterIDSprite,
            initialMapPositionX,
            initialMapPositionY,
            0,
            ALL_DIRECTIONS,
            0
        );

        return generateInputStream(byteArray);
    }

    private byte[] generateByteArray(
        final byte characterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final int actionId,
        final byte directions,
        final int extraBytes
    ) {
        int arrayLength = NORMAL_BYTES + extraBytes;
        byte[] byteArray = new byte[arrayLength];

        byteArray[0] = characterIDSprite;
        InputStreamHelper.setPosition(
            byteArray,
            1,
            initialMapPositionX,
            initialMapPositionY
        );
        byteArray[NUMBER_OF_EVENTS_INDEX] = 1;
        byteArray[NUMBER_OF_EVENTS_INDEX + 1] = directions;
        byteArray[NUMBER_OF_EVENTS_INDEX + 2] = (byte) actionId;

        return byteArray;
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        MapController mapController,
        InputStream inputStream
    ) throws IOException;
}

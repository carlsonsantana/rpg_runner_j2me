package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public abstract class AbstractTeleportFactoryTest extends TestCase {
    private static final int EXAMPLE_MAP_WIDTH = 32;
    private static final int EXAMPLE_MAP_HEIGHT = 32;
    private static final int ADDITIONAL_BYTES = 3;

    public void testTeleportFactory() throws IOException {
        String mapFileName = "example";
        int mapPositionX = RandomGenerator.getRandomPosition();
        int mapPositionY = RandomGenerator.getRandomPosition();
        InputStream inputStream = getInputStream(
            mapFileName,
            mapPositionX,
            mapPositionY
        );

        GameControllerSpy gameController = new GameControllerSpy();
        CharacterElement characterElement = (
            RandomGenerator.generateRandomCharacterElement()
        );
        gameController.setPlayerCharacterElement(characterElement);
        Action action = createAction(inputStream, gameController);

        checkTeleportFactory(
            action,
            gameController,
            characterElement,
            EXAMPLE_MAP_WIDTH,
            EXAMPLE_MAP_HEIGHT,
            mapPositionX,
            mapPositionY
        );
    }

    private InputStream getInputStream(
        final String mapFileName,
        final int mapPositionX,
        final int mapPositionY
    ) {
        int arraySize = InputStreamHelper.getStringLength(mapFileName);
        byte[] byteArray = new byte[arraySize + ADDITIONAL_BYTES];
        InputStreamHelper.setByteArray(byteArray, mapFileName);
        InputStreamHelper.setPosition(
            byteArray,
            arraySize + 1,
            mapPositionX,
            mapPositionY
        );

        return generateInputStream(byteArray);
    }

    private void checkTeleportFactory(
        final Action action,
        final GameControllerSpy gameController,
        final CharacterElement characterElement,
        final int mapWidth,
        final int mapHeight,
        final int mapPositionX,
        final int mapPositionY
    ) {
        action.execute();
        GameCharacter character = characterElement.getCharacter();
        Map map = gameController.getMap();

        Assert.assertTrue(action instanceof Teleport);
        Assert.assertEquals(mapWidth, map.getWidth());
        Assert.assertEquals(mapHeight, map.getHeight());
        Assert.assertEquals(mapPositionX, character.getMapPositionX());
        Assert.assertEquals(mapPositionY, character.getMapPositionY());
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        InputStream inputStream,
        GameController gameController
    ) throws IOException;
}
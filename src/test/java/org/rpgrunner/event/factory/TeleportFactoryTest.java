package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class TeleportFactoryTest extends TestCase {
    private static final int EXAMPLE_MAP_WIDTH = 32;
    private static final int EXAMPLE_MAP_HEIGHT = 32;
    private static final int ADDITIONAL_BYTES = 3;

    public void testTeleportFactory() throws IOException {
        GameControllerSpy gameController = new GameControllerSpy();
        CharacterElement characterElement = (
            RandomGenerator.generateRandomCharacterElement()
        );
        gameController.setPlayerCharacterElement(characterElement);
        String mapFileName = "example";
        int mapPositionX = RandomGenerator.getRandomPosition();
        int mapPositionY = RandomGenerator.getRandomPosition();
        InputStream inputStream = getInputStream(
            mapFileName,
            mapPositionX,
            mapPositionY
        );

        TeleportFactory teleportFactory = new TeleportFactory(gameController);
        Action action = teleportFactory.create(inputStream);
        action.execute();
        GameCharacter character = characterElement.getCharacter();
        Map map = gameController.getMap();

        Assert.assertEquals(EXAMPLE_MAP_WIDTH, map.getHeight());
        Assert.assertEquals(EXAMPLE_MAP_HEIGHT, map.getWidth());
        Assert.assertEquals(mapPositionX, character.getMapPositionX());
        Assert.assertEquals(mapPositionY, character.getMapPositionY());
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

        return new ByteArrayInputStream(byteArray);
    }
}

package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.LocalTeleport;
import org.rpgrunner.test.helper.RandomGenerator;

public class LocalTeleportFactoryTest extends TestCase {
    private static final int ARRAY_SIZE = 2;

    public void testLocalTeleportFactory() throws IOException {
        CharacterElement characterElement = (
            RandomGenerator.generateRandomCharacterElement()
        );
        int mapPositionX = RandomGenerator.getRandomPosition();
        int mapPositionY = RandomGenerator.getRandomPosition();
        InputStream inputStream = getInputStream(mapPositionX, mapPositionY);

        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();
        Action action = localTeleportFactory.create(inputStream);
        ((LocalTeleport) action).setCharacterElement(characterElement);
        action.execute();
        GameCharacter character = characterElement.getCharacter();

        Assert.assertEquals(mapPositionX, character.getMapPositionX());
        Assert.assertEquals(mapPositionY, character.getMapPositionY());
    }

    private InputStream getInputStream(
        final int mapPositionX,
        final int mapPositionY
    ) {
        byte[] byteArray = new byte[2];
        setMapPositions(byteArray, mapPositionX, mapPositionY);

        return new ByteArrayInputStream(byteArray);
    }

    private void setMapPositions(
        final byte[] byteArray,
        final int mapPositionX,
        final int mapPositionY
    ) {
        int i = 0;
        byteArray[i++] = (byte) mapPositionX;
        byteArray[i] = (byte) mapPositionY;
    }
}

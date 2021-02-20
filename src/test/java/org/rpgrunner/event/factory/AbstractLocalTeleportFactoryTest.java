package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.LocalTeleport;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;

public abstract class AbstractLocalTeleportFactoryTest extends TestCase {
    private static final int ARRAY_SIZE = 2;

    public void testLocalTeleportFactory() throws IOException {
        CharacterElement characterElement = (
            RandomGenerator.generateRandomCharacterElement()
        );
        int mapPositionX = RandomGenerator.getRandomPosition();
        int mapPositionY = RandomGenerator.getRandomPosition();
        InputStream inputStream = getInputStream(mapPositionX, mapPositionY);

        Action action = createAction(inputStream);

        checkLocalTeleportFactory(
            action,
            characterElement,
            mapPositionX,
            mapPositionY
        );
    }

    private InputStream getInputStream(
        final int mapPositionX,
        final int mapPositionY
    ) {
        byte[] byteArray = new byte[ARRAY_SIZE];
        InputStreamHelper.setPosition(byteArray, 0, mapPositionX, mapPositionY);

        return generateInputStream(byteArray);
    }

    public static void checkLocalTeleportFactory(
        final Action action,
        final CharacterElement characterElement,
        final int mapPositionX,
        final int mapPositionY
    ) {
        ((LocalTeleport) action).setCharacterElement(characterElement);
        action.execute();
        GameCharacter character = characterElement.getCharacter();

        Assert.assertEquals(mapPositionX, character.getMapPositionX());
        Assert.assertEquals(mapPositionY, character.getMapPositionY());
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        InputStream inputStream
    ) throws IOException;
}

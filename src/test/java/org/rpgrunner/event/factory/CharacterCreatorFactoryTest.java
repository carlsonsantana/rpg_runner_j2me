package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class CharacterCreatorFactoryTest extends TestCase {
    private static final int BYTE_MAX_VALUE = 256;
    private static final int ADDITIONAL_BYTES = 3;
    private Random random;
    private GameControllerSpy gameController;

    public CharacterCreatorFactoryTest() {
        random = new Random();
    }

    public void setUp() {
        gameController = new GameControllerSpy();
    }

    public void test() throws IOException {
        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(gameController)
        );

        String characterFileName = RandomGenerator.getRandomString();
        int initialMapPositionX = random.nextInt(BYTE_MAX_VALUE);
        int initialMapPositionY = random.nextInt(BYTE_MAX_VALUE);
        InputStream inputStream = generateInputStream(
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );

        Action action = characterCreatorFactory.create(inputStream);
        action.execute();

        CharacterElement characterElement = (
            gameController.getLastCharacterElementAdded()
        );
        GameCharacter character = characterElement.getCharacter();

        Assert.assertEquals(characterFileName, character.getFileBaseName());
        Assert.assertEquals(initialMapPositionX, character.getMapPositionX());
        Assert.assertEquals(initialMapPositionY, character.getMapPositionY());
    }

    private InputStream generateInputStream(
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        int stringLength = InputStreamHelper.getStringLength(characterFileName);
        byte[] byteArray = new byte[stringLength + ADDITIONAL_BYTES];
        InputStreamHelper.setByteArray(byteArray, characterFileName);
        setMapPositions(
            byteArray,
            stringLength,
            initialMapPositionX,
            initialMapPositionY
        );

        return new ByteArrayInputStream(byteArray);
    }

    private void setMapPositions(
        final byte[] arrayStream,
        final int stringLength,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        int i = 1;
        arrayStream[stringLength + i++] = (byte) initialMapPositionX;
        arrayStream[stringLength + i++] = (byte) initialMapPositionY;
    }
}

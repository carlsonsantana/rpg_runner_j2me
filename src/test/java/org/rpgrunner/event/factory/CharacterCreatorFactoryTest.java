package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class CharacterCreatorFactoryTest extends TestCase {
    private static final int ADDITIONAL_BYTES = 3;
    private GameControllerSpy gameController;

    public void setUp() {
        gameController = new GameControllerSpy();
    }

    public void testCharacterCreatorFactory() throws IOException {
        String characterFileName = RandomGenerator.getRandomString();
        int initialMapPositionX = RandomGenerator.getRandomPosition();
        int initialMapPositionY = RandomGenerator.getRandomPosition();
        InputStream inputStream = generateInputStream(
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );

        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(gameController)
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
        InputStreamHelper.setPosition(
            byteArray,
            (stringLength + 1),
            initialMapPositionX,
            initialMapPositionY
        );

        return new ByteArrayInputStream(byteArray);
    }
}

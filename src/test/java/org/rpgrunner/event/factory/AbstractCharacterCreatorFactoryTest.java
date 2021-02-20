package org.rpgrunner.event.factory;

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

public abstract class AbstractCharacterCreatorFactoryTest extends TestCase {
    private static final int ADDITIONAL_BYTES = 3;
    private GameControllerSpy gameController;

    public void setUp() {
        gameController = new GameControllerSpy();
    }

    public void testCharacterCreatorFactory() throws IOException {
        String characterFileName = RandomGenerator.getRandomString();
        int initialMapPositionX = RandomGenerator.getRandomPosition();
        int initialMapPositionY = RandomGenerator.getRandomPosition();
        InputStream inputStream = getInputStream(
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );

        Action action = createAction(inputStream, gameController);

        checkCharacterCreatorFactory(
            action,
            gameController,
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );
    }

    private InputStream getInputStream(
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

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        InputStream inputStream,
        GameControllerSpy currentGameController
    ) throws IOException;

    private void checkCharacterCreatorFactory(
        final Action action,
        final GameControllerSpy currentGameController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        action.execute();

        CharacterElement characterElement = getCharacterCreated(
            currentGameController
        );
        GameCharacter character = characterElement.getCharacter();

        Assert.assertTrue(instanceOfCharacterCreator(action));
        Assert.assertEquals(characterFileName, character.getFileBaseName());
        Assert.assertEquals(initialMapPositionX, character.getMapPositionX());
        Assert.assertEquals(initialMapPositionY, character.getMapPositionY());
    }

    protected abstract CharacterElement getCharacterCreated(
        GameControllerSpy currentGameController
    );

    protected abstract boolean instanceOfCharacterCreator(Action action);
}

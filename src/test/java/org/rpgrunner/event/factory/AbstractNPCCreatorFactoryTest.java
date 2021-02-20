package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.AbstractCharacterCreatorTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.test.helper.InputStreamHelper;

public abstract class AbstractNPCCreatorFactoryTest
    extends AbstractCharacterCreatorTest {
    private static final int ADDITIONAL_BYTES = 3;

    protected CharacterCreator createCharacterCreator(
        final GameController gameController,
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
                inputStream,
                gameController
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
        GameController currentGameController
    ) throws IOException;
}

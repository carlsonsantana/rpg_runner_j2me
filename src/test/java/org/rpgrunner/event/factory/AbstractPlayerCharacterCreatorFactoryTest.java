package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.AbstractPlayerCharacterCreatorTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.test.helper.InputStreamHelper;

public abstract class AbstractPlayerCharacterCreatorFactoryTest
    extends AbstractPlayerCharacterCreatorTest {
    private static final int ADDITIONAL_BYTES = 3;

    protected PlayerCharacterCreator createPlayerCharacterCreator(
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
            PlayerCharacterCreator playerCharacterCreator = (
                (PlayerCharacterCreator) createAction(
                    inputStream,
                    gameController
                )
            );

            return playerCharacterCreator;
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

package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;

public class CharacterCreatorFactory {
    private final GameController gameController;

    public CharacterCreatorFactory(final GameController currentGameController) {
        gameController = currentGameController;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String fileBaseName = extractName(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        CharacterCreator characterCreator = new CharacterCreator(
            gameController,
            fileBaseName,
            mapPositionX,
            mapPositionY
        );

        return characterCreator;
    }

    private String extractName(
        final InputStream inputStream
    ) throws IOException {
        int lengthString = inputStream.read();
        byte[] stringBytes = new byte[lengthString];
        inputStream.read(stringBytes);

        return new String(stringBytes);
    }
}

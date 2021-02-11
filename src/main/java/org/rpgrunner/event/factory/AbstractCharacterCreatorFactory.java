package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.Loader;

public abstract class AbstractCharacterCreatorFactory implements ActionFactory {
    private final GameController gameController;

    public AbstractCharacterCreatorFactory(
        final GameController currentGameController
    ) {
        gameController = currentGameController;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String fileBaseName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();

        return create(
            gameController,
            fileBaseName,
            mapPositionX,
            mapPositionY
        );
    }

    protected abstract AbstractCharacterCreator create(
        GameController currentGameController,
        String fileBaseName,
        int mapPositionX,
        int mapPositionY
    );
}

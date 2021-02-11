package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.helper.Loader;

public class TeleportFactory implements ActionFactory {
    private final GameController gameController;

    public TeleportFactory(final GameController currentGameController) {
        gameController = currentGameController;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String mapFileName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        Teleport teleport = new Teleport(
            gameController,
            mapFileName,
            mapPositionX,
            mapPositionY
        );

        return teleport;
    }
}

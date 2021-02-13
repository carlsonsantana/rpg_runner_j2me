package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.helper.Loader;
import org.rpgrunner.map.MapLoader;

public class TeleportFactory implements ActionFactory {
    private final GameController gameController;
    private final MapLoader mapLoader;

    public TeleportFactory(
        final GameController currentGameController,
        final MapLoader currentMapLoader
    ) {
        gameController = currentGameController;
        mapLoader = currentMapLoader;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String mapFileName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        Teleport teleport = new Teleport(
            gameController,
            mapLoader,
            mapFileName,
            mapPositionX,
            mapPositionY
        );

        return teleport;
    }
}

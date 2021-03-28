package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.helper.Loader;
import org.rpgrunner.map.MapLoader;

public class TeleportFactory implements ActionFactory {
    private final MapController mapController;
    private final MapLoader mapLoader;
    private final ActionQueue actionQueue;

    public TeleportFactory(
        final MapController currentMapController,
        final MapLoader currentMapLoader,
        final ActionQueue currentActionQueue
    ) {
        mapController = currentMapController;
        mapLoader = currentMapLoader;
        actionQueue = currentActionQueue;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String mapFileName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        Teleport teleport = new Teleport(
            mapController,
            mapLoader,
            actionQueue,
            mapFileName,
            mapPositionX,
            mapPositionY
        );

        return teleport;
    }
}

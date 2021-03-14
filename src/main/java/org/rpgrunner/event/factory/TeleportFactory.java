package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.helper.Loader;
import org.rpgrunner.map.MapLoader;

public class TeleportFactory implements ActionFactory {
    private final MapController mapController;
    private final MapLoader mapLoader;

    public TeleportFactory(
        final MapController currentMapController,
        final MapLoader currentMapLoader
    ) {
        mapController = currentMapController;
        mapLoader = currentMapLoader;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String mapFileName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        Teleport teleport = new Teleport(
            mapController,
            mapLoader,
            mapFileName,
            mapPositionX,
            mapPositionY
        );

        return teleport;
    }
}

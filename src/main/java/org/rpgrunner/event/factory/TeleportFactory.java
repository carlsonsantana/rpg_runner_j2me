package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.map.MapLoader;

public class TeleportFactory implements IdentifiedActionFactory {
    private static final int ID_VALUE = 4;
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

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) throws IOException {
        byte mapID = (byte) inputStream.read();
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        Teleport teleport = new Teleport(
            mapController,
            mapLoader,
            actionQueue,
            mapID,
            mapPositionX,
            mapPositionY
        );

        return teleport;
    }
}

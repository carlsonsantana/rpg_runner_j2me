package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.LocalTeleport;

public class LocalTeleportFactory implements IdentifiedActionFactory {
    private static final int ID_VALUE = 5;

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) throws IOException {
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        LocalTeleport localTeleport = new LocalTeleport(
            mapPositionX,
            mapPositionY
        );

        return localTeleport;
    }
}

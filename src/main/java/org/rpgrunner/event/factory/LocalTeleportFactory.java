package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.LocalTeleport;

public class LocalTeleportFactory implements ActionFactory {
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

package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.AbstractLocalTeleportTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.LocalTeleport;
import org.rpgrunner.test.helper.InputStreamHelper;

public abstract class AbstractLocalTeleportFactoryTest
    extends AbstractLocalTeleportTest {
    private static final int ARRAY_SIZE = 2;

    protected LocalTeleport createLocalTeleport(
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        InputStream inputStream = getInputStream(
            newMapPositionX,
            newMapPositionY
        );

        try {
            LocalTeleport localTeleport = (LocalTeleport) createAction(
                inputStream
            );

            return localTeleport;
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream getInputStream(
        final int mapPositionX,
        final int mapPositionY
    ) {
        byte[] byteArray = new byte[ARRAY_SIZE];
        InputStreamHelper.setPosition(byteArray, 0, mapPositionX, mapPositionY);

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        InputStream inputStream
    ) throws IOException;
}

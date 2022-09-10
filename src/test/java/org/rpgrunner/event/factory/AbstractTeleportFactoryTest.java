package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.AbstractTeleportTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.test.helper.InputStreamHelper;

public abstract class AbstractTeleportFactoryTest extends AbstractTeleportTest {
    private static final int NUMBER_BYTES = 3;

    protected Teleport createTeleport(
        final MapController mapController,
        final ActionQueue actionQueue,
        final byte mapID,
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        InputStream inputStream = getInputStream(
            mapID,
            newMapPositionX,
            newMapPositionY
        );
        try {
            Teleport teleport = (Teleport) createAction(
                mapController,
                actionQueue,
                inputStream
            );

            return teleport;
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream getInputStream(
        final byte mapID,
        final int mapPositionX,
        final int mapPositionY
    ) {
        byte[] byteArray = new byte[NUMBER_BYTES];
        byteArray[0] = mapID;
        InputStreamHelper.setPosition(
            byteArray,
            1,
            mapPositionX,
            mapPositionY
        );

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        MapController mapController,
        ActionQueue actionQueue,
        InputStream inputStream
    ) throws IOException;
}

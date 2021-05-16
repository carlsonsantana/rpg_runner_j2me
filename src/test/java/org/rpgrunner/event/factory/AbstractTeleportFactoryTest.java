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
    private static final int ADDITIONAL_BYTES = 3;

    protected Teleport createTeleport(
        final MapController mapController,
        final ActionQueue actionQueue,
        final String mapFileName,
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        InputStream inputStream = getInputStream(
            mapFileName,
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
        final String mapFileName,
        final int mapPositionX,
        final int mapPositionY
    ) {
        int arraySize = InputStreamHelper.getStringLength(mapFileName);
        byte[] byteArray = new byte[arraySize + ADDITIONAL_BYTES];
        InputStreamHelper.setByteArray(byteArray, mapFileName);
        InputStreamHelper.setPosition(
            byteArray,
            arraySize + 1,
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

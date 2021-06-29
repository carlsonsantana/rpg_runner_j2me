package org.rpgrunner.event.action;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.map.MapLoader;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class TeleportTest extends AbstractTeleportTest {
    private final MapLoader mapLoader;

    public TeleportTest() {
        mapLoader = new MapLoader(new ActionAbstractFactorySpy());
    }

    protected Teleport createTeleport(
        final MapController mapController,
        final ActionQueue actionQueue,
        final String mapFileName,
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        Teleport teleport = new Teleport(
            mapController,
            mapLoader,
            actionQueue,
            mapFileName,
            newMapPositionX,
            newMapPositionY
        );

        return teleport;
    }
}

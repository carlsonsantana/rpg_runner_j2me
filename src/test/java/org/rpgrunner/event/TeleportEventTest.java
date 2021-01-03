package org.rpgrunner.event;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.GameControllerSpy;

public class TeleportEventTest extends TestCase {
    private GameControllerSpy gameController;
    private TeleportEvent event;

    public void setUp() {
        gameController = new GameControllerSpy();
        event = new TeleportEvent(gameController, "example");
    }

    public void testChangeMap() {
        event.call();

        Map map = gameController.getMap();
        Assert.assertEquals(32, map.getHeight());
        Assert.assertEquals(32, map.getWidth());
    }

    public void testChangeMapTwice() {
        TeleportEvent eventTeleportAnotherMap = new TeleportEvent(
            gameController,
            "another"
        );

        event.call();
        eventTeleportAnotherMap.call();

        Map map = gameController.getMap();
        Assert.assertEquals(16, map.getHeight());
        Assert.assertEquals(16, map.getWidth());
        Assert.assertEquals(2, gameController.getCountMapChanged());
    }

    public void testNotChangeTeleportSameMap() {
        TeleportEvent eventTeleportSameMap = new TeleportEvent(
            gameController,
            "example"
        );

        event.call();
        eventTeleportSameMap.call();

        Map map = gameController.getMap();
        Assert.assertEquals(1, gameController.getCountMapChanged());
    }
}

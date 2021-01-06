package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.GameControllerSpy;

public class TeleportTest extends TestCase {
    private GameControllerSpy gameController;
    private Teleport event;

    public void setUp() {
        gameController = new GameControllerSpy();
        event = new Teleport(gameController, "example");
    }

    public void testChangeMap() {
        event.call();

        Map map = gameController.getMap();
        Assert.assertEquals(32, map.getHeight());
        Assert.assertEquals(32, map.getWidth());
    }

    public void testChangeMapTwice() {
        Teleport eventTeleportAnotherMap = new Teleport(
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
        Teleport eventTeleportSameMap = new Teleport(
            gameController,
            "example"
        );

        event.call();
        eventTeleportSameMap.call();

        Map map = gameController.getMap();
        Assert.assertEquals(1, gameController.getCountMapChanged());
    }
}

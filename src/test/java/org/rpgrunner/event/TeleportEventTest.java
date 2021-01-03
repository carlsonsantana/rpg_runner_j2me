package org.rpgrunner.event;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.GameControllerSpy;

public class TeleportEventTest extends TestCase {
    public void testChangeMap() {
        GameControllerSpy gameController = new GameControllerSpy();
        TeleportEvent event = new TeleportEvent(gameController, "example");

        event.call();

        Map map = gameController.getMap();
        Assert.assertEquals(32, map.getHeight());
        Assert.assertEquals(32, map.getWidth());
    }
}

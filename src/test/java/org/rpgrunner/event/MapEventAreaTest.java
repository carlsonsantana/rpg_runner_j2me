package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.MapEventSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapEventAreaTest extends TestCase {
    private final Random random;

    public MapEventAreaTest() {
        random = new Random();
    }

    public void testReturnsSamePositionValues() {
        int x = random.nextInt();
        int y = random.nextInt();
        int width = random.nextInt();
        int height = random.nextInt();

        MapEventArea mapEventArea = new MapEventArea(x, y, width, height, null);

        Assert.assertEquals(x, mapEventArea.getX());
        Assert.assertEquals(y, mapEventArea.getY());
        Assert.assertEquals(width, mapEventArea.getWidth());
        Assert.assertEquals(height, mapEventArea.getHeight());
    }

    public void testReturnsNullWhenEventIsNotMatching() {
        MapEventSpy mapEvent = new MapEventSpy();
        MapEventArea mapEventArea = new MapEventArea(0, 0, 0, 0, mapEvent);

        Action action = mapEventArea.interact(Direction.UP);

        Assert.assertNull(action);
    }

    public void testReturnsNotNullActionWhenMapEventNotReturnsNull() {
        ActionSpy expectedAction = new ActionSpy();
        MapEventSpy mapEvent = new MapEventSpy();
        mapEvent.setInteractAction(expectedAction);
        MapEventArea mapEventArea = new MapEventArea(0, 0, 0, 0, mapEvent);

        Action action = mapEventArea.interact(Direction.UP);

        Assert.assertEquals(expectedAction, action);
    }
}

package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.MapEventSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapAreaEventListenerTest extends TestCase {
    private final Random random;

    public MapAreaEventListenerTest() {
        random = new Random();
    }

    public void testReturnsSamePositionValues() {
        int x = random.nextInt();
        int y = random.nextInt();
        int width = random.nextInt();
        int height = random.nextInt();

        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            x,
            y,
            width,
            height,
            null
        );

        Assert.assertEquals(x, mapAreaEventListener.getX());
        Assert.assertEquals(y, mapAreaEventListener.getY());
        Assert.assertEquals(width, mapAreaEventListener.getWidth());
        Assert.assertEquals(height, mapAreaEventListener.getHeight());
    }

    public void testReturnsNullWhenEventIsNotMatching() {
        MapEventSpy mapEvent = new MapEventSpy();
        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            0,
            0,
            0,
            0,
            mapEvent
        );

        Action action = mapAreaEventListener.interact(Direction.UP);

        Assert.assertNull(action);
    }

    public void testReturnsNotNullActionWhenMapEventNotReturnsNull() {
        ActionSpy expectedAction = new ActionSpy();
        MapEventSpy mapEvent = new MapEventSpy();
        mapEvent.setInteractAction(expectedAction);
        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            0,
            0,
            0,
            0,
            mapEvent
        );

        Action action = mapAreaEventListener.interact(Direction.UP);

        Assert.assertEquals(expectedAction, action);
    }
}

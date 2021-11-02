package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.test.mock.event.MapEventSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapAreaEventListenerTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MAX_ARRAY_SIZE = 100;
    private static final int MIN_ARRAY_SIZE = 10;
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

    public void testReturnsNullActionWhenNotAreEvents() {
        MapEvent[] emptyMapEvents = new MapEvent[0];
        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            0,
            0,
            0,
            0,
            emptyMapEvents
        );

        Action action = mapAreaEventListener.interact(Direction.UP);
        Assert.assertTrue(action instanceof NullAction);
    }

    public void testReturnsNullActionWhenNotAreEventsMatching() {
        MapEventSpy mapEvent = new MapEventSpy();
        MapEvent[] mapEvents = new MapEvent[] {mapEvent};
        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            0,
            0,
            0,
            0,
            mapEvents
        );

        Action action = mapAreaEventListener.interact(Direction.UP);
        Assert.assertTrue(action instanceof NullAction);
    }

    public void testReturnsFirstNotNullActionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkReturnsFirstNotNullAction();
        }
    }

    private void checkReturnsFirstNotNullAction() {
        ActionSpy expectedAction = new ActionSpy();
        MapEventSpy mapEvent = new MapEventSpy();
        mapEvent.setInteractAction(expectedAction);
        MapEvent[] mapEvents = getRandomMapEvents(mapEvent);
        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            0,
            0,
            0,
            0,
            mapEvents
        );

        Action action = mapAreaEventListener.interact(Direction.UP);
        Assert.assertEquals(expectedAction, action);
    }

    private MapEvent[] getRandomMapEvents(final MapEvent expectedMapEvent) {
        int arraySize = random.nextInt(MAX_ARRAY_SIZE) + MIN_ARRAY_SIZE;
        int filledIndex = random.nextInt(arraySize - 1);
        MapEvent[] mapEvents = new MapEvent[arraySize];

        for (int i = 0; i < arraySize; i++) {
            if (i < filledIndex) {
                MapEvent nullEvent = new MapEventSpy();
                mapEvents[i] = nullEvent;
            } else if (i == filledIndex) {
                mapEvents[i] = expectedMapEvent;
            } else {
                MapEventSpy notNullEvent = new MapEventSpy();
                notNullEvent.setInteractAction(new ActionSpy());
                mapEvents[i] = notNullEvent;
            }
        }

        return mapEvents;
    }
}

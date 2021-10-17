package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.test.mock.event.MapEventSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapEventListenerTest extends TestCase {
    private static final int MAX_ARRAY_SIZE = 100;
    private static final int MIN_ARRAY_SIZE = 10;
    private final Random random;
    private MapEventSpy event1;
    private MapEventSpy event2;
    private ActionSpy action1;
    private ActionSpy action2;

    public MapEventListenerTest() {
        random = new Random();
    }

    public void setUp() {
        event1 = new MapEventSpy();
        event2 = new MapEventSpy();
        action1 = new ActionSpy();
        action2 = new ActionSpy();

        event1.setInteractAction(action1);
        event2.setInteractAction(action2);
    }

    public void testReturnsNullActionWhenNotAreEvents() {
        MapEvent[] mapEvents = new MapEvent[0];
        MapEventListener mapEventListener = new MapEventListener(mapEvents);

        Action action = mapEventListener.interact((byte) 0);

        Assert.assertTrue(action instanceof NullAction);
    }

    public void testReturnsFirstNotNullAction() {
        MapEvent[] mapEvents = getRandomMapEvent();
        MapEventListener mapEventListener = new MapEventListener(mapEvents);

        Action action = mapEventListener.interact((byte) 0);

        Assert.assertSame(action1, action);
    }

    private MapEvent[] getRandomMapEvent() {
        int arraySize = random.nextInt(MAX_ARRAY_SIZE) + MIN_ARRAY_SIZE;
        int filledIndex = random.nextInt(arraySize - 1);
        MapEvent[] mapEvents = new MapEvent[arraySize];
        MapEvent nullEvent = new MapEventSpy();

        for (int i = 0; i < arraySize; i++) {
            if (i < filledIndex) {
                mapEvents[i] = nullEvent;
            } else if (i == filledIndex) {
                mapEvents[i] = event1;
            } else {
                mapEvents[i] = event2;
            }
        }

        return mapEvents;
    }
}

package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.MapEventSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class CharacterEventListenerTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MAX_ARRAY_SIZE = 100;
    private static final int MIN_ARRAY_SIZE = 10;
    private final Random random;

    public CharacterEventListenerTest() {
        random = new Random();
    }

    public void testReturnsNullWhenNotAreEvents() {
        MapEvent[] mapEvents = new MapEvent[0];
        CharacterEventListener characterEventListener = (
            new CharacterEventListener(mapEvents)
        );

        Action action = characterEventListener.interact((byte) 0);

        Assert.assertNull(action);
    }

    public void testReturnsNullWhenNotAreEventsMatching() {
        MapEventSpy mapEvent = new MapEventSpy();
        MapEvent[] mapEvents = new MapEvent[] {mapEvent};
        CharacterEventListener characterEventListener = (
            new CharacterEventListener(mapEvents)
        );

        Action action = characterEventListener.interact(Direction.UP);

        Assert.assertNull(action);
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
        CharacterEventListener characterEventListener = (
            new CharacterEventListener(mapEvents)
        );

        Action action = characterEventListener.interact((byte) 0);

        Assert.assertSame(expectedAction, action);
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

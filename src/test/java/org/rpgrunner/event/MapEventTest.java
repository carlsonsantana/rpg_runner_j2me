package org.rpgrunner.event;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapEventTest extends TestCase {
    private ActionSpy actionSpy;
    private byte interactDirection;

    public void setUp() {
        actionSpy = new ActionSpy();
        interactDirection = RandomGenerator.getRandomDirection();
    }

    public void testReturnsNullActionWhenDirectionDoesNotMatch() {
        byte eventDirections = (byte) ~interactDirection;
        MapEvent mapEvent = new MapEvent(actionSpy, eventDirections);
        Action action = mapEvent.interact(interactDirection);

        Assert.assertEquals(null, action);
    }

    public void testReturnsSameActionWhenSameDirectionIsPassed() {
        byte eventDirections = interactDirection;
        MapEvent mapEvent = new MapEvent(actionSpy, eventDirections);
        Action action = mapEvent.interact(interactDirection);

        Assert.assertSame(actionSpy, action);
    }

    public void testReturnsSameActionWhenDirectionMatches() {
        byte directions = (byte) ~Direction.invertDirection(interactDirection);
        MapEvent mapEvent = new MapEvent(actionSpy, directions);
        Action action = mapEvent.interact(interactDirection);

        Assert.assertSame(actionSpy, action);
    }
}

package org.rpgrunner.event;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapEventTest extends TestCase {
    private ActionSpy actionSpy;
    private byte interactDirection;

    public void setUp() {
        actionSpy = new ActionSpy();
        interactDirection = RandomGenerator.getRandomDirection();
    }

    public void testReturnsNullActionWhenDirectionDoesNotMatchLoop() {
        byte eventDirections = (byte) ~interactDirection;
        MapEvent mapEvent = new MapEvent(actionSpy, eventDirections);
        Action action = mapEvent.interact(interactDirection);

        Assert.assertNotSame(actionSpy, action);
        Assert.assertTrue(action instanceof NullAction);
    }

    public void testReturnsSameActionWhenSameDirectionIsPassedLoop() {
        byte eventDirections = interactDirection;
        MapEvent mapEvent = new MapEvent(actionSpy, eventDirections);
        Action action = mapEvent.interact(interactDirection);

        Assert.assertSame(actionSpy, action);
    }

    public void testReturnsSameActionWhenDirectionMatchesLoop() {
        byte directions = (byte) ~Direction.invertDirection(interactDirection);
        MapEvent mapEvent = new MapEvent(actionSpy, directions);
        Action action = mapEvent.interact(interactDirection);

        Assert.assertSame(actionSpy, action);
    }
}

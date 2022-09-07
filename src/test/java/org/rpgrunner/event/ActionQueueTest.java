package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.event.action.ActionSpy;

public class ActionQueueTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MIN_NUMBER_ACTIONS = 2;
    private static final int MAX_NUMBER_ACTIONS_EXTRA = 18;
    private final Random random;
    private ActionQueue actionQueue;

    public ActionQueueTest() {
        random = new Random();
    }

    public void setUp() {
        actionQueue = new ActionQueue();
    }

    public void testDoNotThrowAnExceptionWhenQueueIsEmpty() {
        actionQueue.execute();
    }

    public void testDoNotExecuteActionOnPush() {
        ActionSpy action = new ActionSpy();
        actionQueue.push(action);

        Assert.assertFalse(action.isExecuteCalled());
    }

    public void testExecuteActionOfQueueWithOneAction() {
        ActionSpy action = new ActionSpy();
        actionQueue.push(action);

        actionQueue.execute();
        Assert.assertTrue(action.isExecuteCalled());
    }

    public void testExecuteActionsOfQueueWithMoreThanOneActionInOrderLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            ActionSpy.clearCallOrder();
            checkExecuteActionsOfQueueWithMoreThanOneActionInOrder();
        }
    }

    private void checkExecuteActionsOfQueueWithMoreThanOneActionInOrder() {
        int numberOfActions = (
            random.nextInt(MAX_NUMBER_ACTIONS_EXTRA) + MIN_NUMBER_ACTIONS
        );
        ActionSpy[] actions = new ActionSpy[numberOfActions];

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = new ActionSpy();

            actions[i] = action;
            actionQueue.push(action);
        }

        actionQueue.execute();

        for (int i = 0; i < numberOfActions; i++) {
            int callOrder = i + 1;
            ActionSpy action = actions[i];

            Assert.assertTrue(action.isExecuteCalled());
            Assert.assertEquals(callOrder, action.getCallOrder());
        }
    }

    public void testClearActionsAfterExecuteLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkClearActionsAfterExecute();
        }
    }

    private void checkClearActionsAfterExecute() {
        int numberOfActions = (
            random.nextInt(MAX_NUMBER_ACTIONS_EXTRA) + MIN_NUMBER_ACTIONS
        );
        int lastActionNumber = numberOfActions + 1;
        ActionSpy[] actions = new ActionSpy[numberOfActions];

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = new ActionSpy();

            actions[i] = action;
            actionQueue.push(action);
        }

        actionQueue.execute();
        actionQueue.execute();

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = actions[i];

            Assert.assertEquals(1, action.getTimesCalled());
        }
    }

    public void testAllowExecuteAgainAnActionIfAddedAgainLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkAllowExecuteAgainAnActionIfAddedAgain();
        }
    }

    private void checkAllowExecuteAgainAnActionIfAddedAgain() {
        int numberOfActions = (
            random.nextInt(MAX_NUMBER_ACTIONS_EXTRA) + MIN_NUMBER_ACTIONS
        );
        ActionSpy[] actions = new ActionSpy[numberOfActions];

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = new ActionSpy();

            actions[i] = action;
            actionQueue.push(action);
        }

        actionQueue.execute();

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = (ActionSpy) actions[i];

            actionQueue.push(action);
        }

        actionQueue.execute();

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = actions[i];

            Assert.assertEquals(2, action.getTimesCalled());
        }
    }
}

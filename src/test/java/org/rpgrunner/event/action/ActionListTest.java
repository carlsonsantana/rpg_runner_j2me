package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.event.action.ActionSpy;

public class ActionListTest extends TestCase {
    private static final int MINIMUM_NUMBER_ACTIONS = 2;
    private static final int MAXIMUM_NUMBER_ACTIONS = 100;
    private Random random;

    public ActionListTest() {
        random = new Random();
    }

    public void testExecuteEmptyActionList() {
        Action[] actionArray = new Action[0];
        ActionList actionList = new ActionList(actionArray);
        actionList.execute();
    }

    public void testDontExecuteActionListWhenInitialize() {
        ActionSpy action = new ActionSpy();
        Action[] actionArray = new Action[1];
        actionArray[0] = action;
        ActionList actionList = new ActionList(actionArray);
        Assert.assertFalse(action.isExecuteCalled());
    }

    public void testExecuteActionListWithOneAction() {
        ActionSpy action = new ActionSpy();
        Action[] actionArray = new Action[1];
        actionArray[0] = action;
        ActionList actionList = new ActionList(actionArray);
        actionList.execute();

        Assert.assertTrue(action.isExecuteCalled());
    }

    public void testExecuteActionListWithManyActions() {
        ActionSpy.clearCallOrder();
        int numberOfActions = (
            random.nextInt(MAXIMUM_NUMBER_ACTIONS)
            + MINIMUM_NUMBER_ACTIONS
        );
        ActionSpy[] actionArray = new ActionSpy[numberOfActions];

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = new ActionSpy();
            actionArray[i] = action;
        }

        ActionList actionList = new ActionList(actionArray);
        actionList.execute();

        for (int i = 0; i < numberOfActions; i++) {
            ActionSpy action = actionArray[i];
            Assert.assertTrue(action.isExecuteCalled());
            Assert.assertEquals(i + 1, action.getCallOrder());
        }
    }
}

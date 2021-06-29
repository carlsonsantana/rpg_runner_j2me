package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.event.action.ActionSpy;

public abstract class AbstractActionListTest extends TestCase {
    private static final int MINIMUM_NUMBER_ACTIONS = 2;
    private static final int MAXIMUM_NUMBER_ACTIONS = 100;
    private Random random;

    public AbstractActionListTest() {
        random = new Random();
    }

    public void testExecuteEmptyActionList() {
        ActionList actionList = createActionList(0);

        actionList.execute();
    }

    public void testDontExecuteActionListWhenInitialize() {
        ActionList actionList = createActionList(1);
        ActionSpy action = getLastActionsCreated()[0];

        Assert.assertFalse(action.isExecuteCalled());
    }

    public void testExecuteActionListWithOneAction() {
        ActionList actionList = createActionList(1);
        actionList.execute();
        ActionSpy action = getLastActionsCreated()[0];

        Assert.assertTrue(action.isExecuteCalled());
    }

    public void testExecuteActionListWithManyActions() {
        int numberActions = (
            random.nextInt(MAXIMUM_NUMBER_ACTIONS)
            + MINIMUM_NUMBER_ACTIONS
        );

        ActionSpy.clearCallOrder();
        ActionList actionList = createActionList(numberActions);
        actionList.execute();
        ActionSpy[] actionArray = getLastActionsCreated();

        for (int i = 0; i < numberActions; i++) {
            ActionSpy action = actionArray[i];
            Assert.assertTrue(action.isExecuteCalled());
            Assert.assertEquals(i + 1, action.getCallOrder());
        }
    }

    protected abstract ActionList createActionList(int numberActions);

    protected abstract ActionSpy[] getLastActionsCreated();
}

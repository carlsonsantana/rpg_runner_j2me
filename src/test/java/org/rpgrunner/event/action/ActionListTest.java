package org.rpgrunner.event.action;

import org.rpgrunner.test.mock.event.action.ActionSpy;

public class ActionListTest extends AbstractActionListTest {
    private ActionSpy[] actionArray;

    protected ActionList createActionList(final int numberActions) {
        actionArray = new ActionSpy[numberActions];

        for (int i = 0; i < numberActions; i++) {
            ActionSpy action = new ActionSpy();
            actionArray[i] = action;
        }

        return new ActionList(actionArray);
    }

    protected ActionSpy[] getLastActionsCreated() {
        return actionArray;
    }
}

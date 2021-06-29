package org.rpgrunner.test.mock.event.action;

import org.rpgrunner.event.action.ActionList;

public class ActionListSpy extends ActionList {
    private boolean executeCalled;

    public ActionListSpy() {
        super(null);
        executeCalled = false;
    }

    public void execute() {
        executeCalled = true;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }
}

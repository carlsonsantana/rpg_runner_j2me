package org.rpgrunner.test.mock.event.action;

import org.rpgrunner.event.action.Action;

public class ActionSpy implements Action {
    private static int numberOfExecuteCalls = 0;
    private boolean executeCalled;
    private int callOrder;

    public ActionSpy() {
        executeCalled = false;
    }

    public void execute() {
        executeCalled = true;
        callOrder = ++numberOfExecuteCalls;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public int getCallOrder() {
        return callOrder;
    }

    public static void clearCallOrder() {
        numberOfExecuteCalls = 0;
    }
}

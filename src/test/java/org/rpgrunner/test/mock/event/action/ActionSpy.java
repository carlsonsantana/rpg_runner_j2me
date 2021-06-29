package org.rpgrunner.test.mock.event.action;

import org.rpgrunner.event.action.Action;

public class ActionSpy implements Action {
    private static int numberOfExecuteCalls = 0;
    private boolean executeCalled;
    private int callOrder;
    private int timesCalled;

    public ActionSpy() {
        executeCalled = false;
        timesCalled = 0;
    }

    public void execute() {
        executeCalled = true;
        callOrder = ++numberOfExecuteCalls;
        timesCalled++;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public int getCallOrder() {
        return callOrder;
    }

    public int getTimesCalled() {
        return timesCalled;
    }

    public static void clearCallOrder() {
        numberOfExecuteCalls = 0;
    }
}

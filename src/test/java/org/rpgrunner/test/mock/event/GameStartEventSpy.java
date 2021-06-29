package org.rpgrunner.test.mock.event;

import org.rpgrunner.event.GameStartEvent;
import org.rpgrunner.event.factory.ActionAbstractFactory;

public class GameStartEventSpy extends GameStartEvent {
    private boolean executeCalled = false;

    public void execute(final ActionAbstractFactory actionAbstractFactory) {
        executeCalled = true;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }
}

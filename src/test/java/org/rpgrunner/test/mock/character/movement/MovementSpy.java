package org.rpgrunner.test.mock.character.movement;

import org.rpgrunner.character.movement.MovementCommand;

public class MovementSpy implements MovementCommand {
    private boolean executeCalled;

    public MovementSpy() {
        executeCalled = false;
    }

    public void execute() {
        executeCalled = true;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }
}

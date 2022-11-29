package org.rpgrunner.test.mock.character.movement;

import org.rpgrunner.character.movement.PlayerMovement;

public class PlayerMovementSpy extends PlayerMovement {
    private boolean executeCalled;

    public PlayerMovementSpy() {
        super(null, null, null);
        executeCalled = false;
    }

    public void execute() {
        executeCalled = true;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }
}

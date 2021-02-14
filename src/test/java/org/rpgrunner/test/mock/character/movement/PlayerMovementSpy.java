package org.rpgrunner.test.mock.character.movement;

import org.rpgrunner.character.movement.PlayerMovement;

public class PlayerMovementSpy extends MovementSpy implements PlayerMovement {
    private int pressedKey;
    private int releasedKey;

    public PlayerMovementSpy() {
        super();
    }

    public void pressKey(final int key) {
        pressedKey = key;
    }

    public int getPressedKey() {
        return pressedKey;
    }

    public void releaseKey(final int key) {
        releasedKey = key;
    }

    public int getReleasedKey() {
        return releasedKey;
    }
}

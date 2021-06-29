package org.rpgrunner.test.mock.character.movement;

import org.rpgrunner.character.movement.PlayerMovement;

public class PlayerMovementSpy extends MovementSpy implements PlayerMovement {
    private int pressedKey;
    private int releasedKey;
    private boolean releaseAllKeysCalled;

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

    public void releaseAllKeys() {
        releaseAllKeysCalled = true;
    }

    public boolean isReleaseAllKeysCalled() {
        return releaseAllKeysCalled;
    }
}

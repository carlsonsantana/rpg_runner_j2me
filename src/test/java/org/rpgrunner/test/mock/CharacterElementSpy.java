package org.rpgrunner.test.mock;

import org.rpgrunner.character.CharacterElement;

public class CharacterElementSpy extends CharacterElement {
    private boolean onMoveCalled;

    public CharacterElementSpy() {
        super(null, null, null, null);
        onMoveCalled = false;
    }

    public void onMove() {
        onMoveCalled = true;
    }

    public boolean isOnMoveCalled() {
        return onMoveCalled;
    }
}

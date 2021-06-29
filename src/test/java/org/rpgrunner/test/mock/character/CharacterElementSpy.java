package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterElement;

public class CharacterElementSpy extends CharacterElement {
    private boolean onMoveCalled;
    private boolean interactCalled;

    public CharacterElementSpy() {
        super(null, null, null, null);
        onMoveCalled = false;
        interactCalled = false;
    }

    public void onMove() {
        onMoveCalled = true;
    }

    public boolean isOnMoveCalled() {
        return onMoveCalled;
    }

    public void interact() {
        interactCalled = true;
    }

    public boolean isInteractCalled() {
        return interactCalled;
    }
}

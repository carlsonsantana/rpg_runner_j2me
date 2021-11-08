package org.rpgrunner.test.mock.event;

import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.action.Action;

public class CharacterEventListenerSpy extends CharacterEventListener {
    private Action action;

    public CharacterEventListenerSpy() {
        super(null);
    }

    public Action interact(final byte direction) {
        return action;
    }

    public void setInteractAction(final Action newAction) {
        action = newAction;
    }
}

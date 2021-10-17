package org.rpgrunner.test.mock.event;

import org.rpgrunner.event.MapEventListener;
import org.rpgrunner.event.action.Action;

public class MapEventListenerSpy extends MapEventListener {
    private Action action;

    public MapEventListenerSpy() {
        super(null);
    }

    public Action interact(final byte direction) {
        return action;
    }

    public void setInteractAction(final Action newAction) {
        action = newAction;
    }
}

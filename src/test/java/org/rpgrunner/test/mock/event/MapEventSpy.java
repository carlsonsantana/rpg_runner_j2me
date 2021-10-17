package org.rpgrunner.test.mock.event;

import org.rpgrunner.event.MapEvent;
import org.rpgrunner.event.action.Action;

public class MapEventSpy extends MapEvent {
    private Action action;

    public MapEventSpy() {
        super(null, (byte) 0);
    }

    public Action interact(final byte direction) {
        return action;
    }

    public void setInteractAction(final Action newAction) {
        action = newAction;
    }
}

package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;

public class MapEvent {
    private final Action action;
    private final byte directions;

    public MapEvent(final Action eventAction, final byte eventDirections) {
        action = eventAction;
        directions = eventDirections;
    }

    public Action interact(final byte direction) {
        if ((directions & direction) != 0) {
            return action;
        }

        return null;
    }
}

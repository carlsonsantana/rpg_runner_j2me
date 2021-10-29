package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public class CharacterEventListener {
    private static final NullAction NULL_ACTION = new NullAction();
    private final MapEvent[] events;

    public CharacterEventListener(final MapEvent[] eventsList) {
        events = eventsList;
    }

    public Action interact(final byte direction) {
        for (int i = 0, length = events.length; i < length; i++) {
            Action action = events[i].interact(direction);

            if (action != null) {
                return action;
            }
        }

        return NULL_ACTION;
    }
}

package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public class MapAreaEventListener {
    private static final NullAction NULL_ACTION = new NullAction();
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final MapEvent[] events;

    public MapAreaEventListener(
        final int tilePositionX,
        final int tilePositionY,
        final int tilesWidth,
        final int tilesHeight,
        final MapEvent[] eventsList
    ) {
        x = tilePositionX;
        y = tilePositionY;
        width = tilesWidth;
        height = tilesHeight;
        events = eventsList;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Action interact(final byte direction) {
        for (int i = 0, length = events.length; i < length; i++) {
            MapEvent event = events[i];

            Action action = event.interact(direction);

            if (action != null) {
                return action;
            }
        }

        return NULL_ACTION;
    }
}

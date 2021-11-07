package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;

public class MapEventArea extends MapEvent {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public MapEventArea(
        final int tilePositionX,
        final int tilePositionY,
        final int tilesWidth,
        final int tilesHeight,
        final byte eventDirections,
        final Action eventAction
    ) {
        super(eventAction, eventDirections);

        x = tilePositionX;
        y = tilePositionY;
        width = tilesWidth;
        height = tilesHeight;
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
}

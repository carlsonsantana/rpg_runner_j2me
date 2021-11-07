package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;

public class MapEventArea {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final MapEvent mapEvent;

    public MapEventArea(
        final int tilePositionX,
        final int tilePositionY,
        final int tilesWidth,
        final int tilesHeight,
        final MapEvent event
    ) {
        x = tilePositionX;
        y = tilePositionY;
        width = tilesWidth;
        height = tilesHeight;
        mapEvent = event;
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
        return mapEvent.interact(direction);
    }
}

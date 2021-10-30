package org.rpgrunner.event;

public class MapAreaEventListener {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public MapAreaEventListener(
        final int tilePositionX,
        final int tilePositionY,
        final int tilesWidth,
        final int tilesHeight
    ) {
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

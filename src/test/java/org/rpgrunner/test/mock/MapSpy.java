package org.rpgrunner.test.mock;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.Layer;

public class MapSpy extends Map {
    private boolean canMove;
    private int width;
    private int height;

    public MapSpy() {
        super(null);
        width = 0;
        height = 0;
    }

    public void setWidth(final int newWidth) {
        width = newWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(final int newHeight) {
        height = newHeight;
    }

    public int getHeight() {
        return height;
    }

    public Layer[] getLayers() {
        return null;
    }

    public void setCanMoveTo(final boolean newCanMove) {
        canMove = newCanMove;
    }

    public boolean canMoveTo(
        final int fromX,
        final int fromY,
        final int toX,
        final int toY,
        final byte direction
    ) {
        return canMove;
    }
}

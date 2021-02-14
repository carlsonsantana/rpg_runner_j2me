package org.rpgrunner.test.mock.map;

import org.rpgrunner.map.Layer;
import org.rpgrunner.map.Map;

public class MapSpy extends Map {
    private boolean canMove;
    private int width;
    private int height;
    private boolean startActionsCalled;

    public MapSpy() {
        super(null, null, null);
        width = 0;
        height = 0;
        startActionsCalled = false;
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

    public void executeStartActions() {
        startActionsCalled = true;
    }

    public boolean isStartActionsCalled() {
        return startActionsCalled;
    }
}

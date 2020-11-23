package org.rpgrunner.test.mock;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.Layer;

public class MapSpy extends Map {
    private boolean canMove;

    public MapSpy() {
        super(null);
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
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

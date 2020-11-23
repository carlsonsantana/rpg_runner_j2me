package org.rpgrunner.test.mock;

import org.rpgrunner.game.map.Layer;
import org.rpgrunner.game.tileset.TileSet;

public class LayerSpy extends Layer {
    private int width;
    private int height;
    private boolean canMove;

    public LayerSpy() {
        super(null, null);
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

    public byte[][] getTileMap() {
        return null;
    }

    public TileSet getTileSet() {
        return null;
    }

    public void setCanMove(final boolean newCanMove) {
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
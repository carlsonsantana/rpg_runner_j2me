package org.rpgrunner.test.mock.map;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.map.Layer;
import org.rpgrunner.map.TileSet;

public class LayerSpy extends Layer {
    private int width;
    private int height;
    private boolean canMove;

    public LayerSpy() {
        super(null, null, 0, 0);
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

    public byte[] getTileMap() {
        return null;
    }

    public TileSet getTileSet() {
        return null;
    }

    public void setCanMove(final boolean newCanMove) {
        canMove = newCanMove;
    }

    public boolean canMove(final CharacterAnimation character) {
        return canMove;
    }
}

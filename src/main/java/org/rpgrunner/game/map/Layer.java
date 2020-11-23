package org.rpgrunner.game.map;

import org.rpgrunner.game.Direction;
import org.rpgrunner.game.tileset.TileSet;

public class Layer {
    private final TileSet tileSet;
    private final byte[][] tileMap;

    public Layer(final TileSet mapTileSet, final byte[][] mapTileMap) {
        tileSet = mapTileSet;
        tileMap = mapTileMap;
    }

    public int getWidth() {
        return tileMap[0].length;
    }

    public int getHeight() {
        return tileMap.length;
    }

    public byte[][] getTileMap() {
        return tileMap;
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    public boolean canMoveTo(
        final int fromX,
        final int fromY,
        final int toX,
        final int toY,
        final byte direction
    ) {
        return (
            isValidPosition(toX, toY)
            && (tileSet.canPassOn(tileMap[fromY][fromX], direction))
            && (
                tileSet.canPassOn(
                    tileMap[toY][toX],
                    Direction.invertDirection(direction)
                )
            )
        );
    }

    private boolean isValidPosition(final int x, final int y) {
        return ((x >= 0) && (y >= 0) && (x < getWidth()) && (y < getHeight()));
    }
}

package org.rpgrunner.game.map;

import org.rpgrunner.game.tileset.TileSet;

public class Map {
    private final TileSet tileSet;
    private final byte[][] tileMap;

    public Map(final TileSet mapTileSet, final byte[][] mapTileMap) {
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
            (toX >= 0)
            && (toY >= 0)
            && (toX < getWidth())
            && (toY < getHeight())
            && (!tileSet.canColideOn(
                tileMap[fromY][fromX],
                tileMap[toY][toX],
                direction
            ))
        );
    }
}

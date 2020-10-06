package org.rpgrunner.game.map;

import org.rpgrunner.game.tileset.TileSet;

public class Map {
    private final String tileSetFileName;
    private final byte[][] tileMap;
    private final TileSet tileSet;

    public Map(
        final String mapTileTileSetFileName,
        final byte[][] mapTileMap,
        final TileSet mapTileSet
    ) {
        tileSetFileName = mapTileTileSetFileName;
        tileMap = mapTileMap;
        tileSet = mapTileSet;
    }

    public String getTileSetFileName() {
        return tileSetFileName;
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

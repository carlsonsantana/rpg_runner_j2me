package org.rpgrunner.game.map;

public class Map {
    private final String tileSetFileName;
    private final byte[][] tileMap;

    public Map(
        final String mapTileTileSetFileName,
        final byte[][] mapTileMap
    ) {
        tileSetFileName = mapTileTileSetFileName;
        tileMap = mapTileMap;
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
}

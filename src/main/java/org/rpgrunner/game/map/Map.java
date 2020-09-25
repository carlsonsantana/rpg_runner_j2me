package org.rpgrunner.game.map;

public class Map {
    private final String tileSetFileName;
    private final byte[][] background;

    public Map(
        final String backgroundTileSetFileName,
        final byte[][] mapBackground
    ) {
        tileSetFileName = backgroundTileSetFileName;
        background = mapBackground;
    }

    public String getTileSetFileName() {
        return tileSetFileName;
    }

    public int getWidth() {
        return background[0].length;
    }

    public int getHeight() {
        return background.length;
    }
}

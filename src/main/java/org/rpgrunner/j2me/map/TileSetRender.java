package org.rpgrunner.j2me.map;

import java.io.IOException;

import javax.microedition.lcdui.Image;

import org.rpgrunner.map.TileSet;
import org.rpgrunner.map.TileSetLoader;

public class TileSetRender {
    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;
    private static final String TILESET_IMAGE_EXTENSION = ".png";

    private TileSetRender() { }

    public static Image loadImage(final TileSet tileSet) {
        String tileSetFileName = (
            TileSetLoader.TILESET_DIRECTORY
            + tileSet.getID()
            + TILESET_IMAGE_EXTENSION
        );

        try {
            return Image.createImage(tileSetFileName);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

package org.rpgrunner.game.j2me;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapRender;

public class MapRenderImpl implements MapRender {
    private static final int HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final String TILESET_DIRECTORY = "/tilesets/";
    private static final String TILESET_EXTENSION = ".png";
    private final Graphics graphics;
    private final Map map;
    private final TiledLayer tiledLayer;

    public MapRenderImpl(final Graphics gameGraphics, final Map gameMap) {
        graphics = gameGraphics;
        map = gameMap;

        tiledLayer = new TiledLayer(
            map.getWidth(),
            map.getHeight(),
            loadTileSetImage(),
            WIDTH,
            HEIGHT
        );
        fillLayer();
    }

    private Image loadTileSetImage() {
        String tileSetFileName = (
            TILESET_DIRECTORY
            + map.getTileSetFileName()
            + TILESET_EXTENSION
        );

        try {
            return Image.createImage(tileSetFileName);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private void fillLayer() {
        int height = map.getHeight();
        int width = map.getWidth();
        byte[][] tileMap = map.getTileMap();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tileMap[y][x] > 0) {
                    tiledLayer.setCell(x, y, tileMap[y][x]);
                }
            }
        }
    }

    public void render() {
        tiledLayer.paint(graphics);
    }
}

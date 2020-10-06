package org.rpgrunner.game.j2me;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapRender;

public class MapRenderImpl implements MapRender {
    private final Graphics graphics;
    private final Map map;
    private final TiledLayer tiledLayer;

    public MapRenderImpl(final Graphics gameGraphics, final Map gameMap) {
        graphics = gameGraphics;
        map = gameMap;
        Image tileSetImage = TileSetRender.loadImage(map.getTileSet());

        tiledLayer = new TiledLayer(
            map.getWidth(),
            map.getHeight(),
            tileSetImage,
            TileSetRender.TILE_WIDTH,
            TileSetRender.TILE_HEIGHT
        );
        fillLayer();
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

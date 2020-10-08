package org.rpgrunner.game.j2me;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapRender;

public class MapRenderImpl implements MapRender {
    private final Graphics graphics;
    private final Map map;
    private final LayerManager layerManager;
    private final int screenWidth;
    private final int screenHeight;

    public MapRenderImpl(
        final Graphics gameGraphics,
        final Map gameMap,
        final int deviceScreenWidth,
        final int deviceScreenHeight
    ) {
        graphics = gameGraphics;
        map = gameMap;
        screenWidth = deviceScreenWidth;
        screenHeight = deviceScreenHeight;
        Image tileSetImage = TileSetRender.loadImage(map.getTileSet());

        TiledLayer tiledLayer = new TiledLayer(
            map.getWidth(),
            map.getHeight(),
            tileSetImage,
            TileSetRender.TILE_WIDTH,
            TileSetRender.TILE_HEIGHT
        );
        fillLayer(tiledLayer);

        layerManager = new LayerManager();
        layerManager.append(tiledLayer);
    }

    private void fillLayer(final TiledLayer tiledLayer) {
        int height = map.getHeight();
        int width = map.getWidth();
        byte[][] tileMap = map.getTileMap();
        for (int row = 0; row < height; row++) {
            for (int cell = 0; cell < width; cell++) {
                if (tileMap[row][cell] > 0) {
                    tiledLayer.setCell(cell, row, tileMap[row][cell]);
                }
            }
        }
    }

    public void render() {
        layerManager.paint(graphics, 0, 0);
    }

    public void setPosition(final int x, final int y) {
        layerManager.setViewWindow(x, y, screenWidth, screenHeight);
    }
}

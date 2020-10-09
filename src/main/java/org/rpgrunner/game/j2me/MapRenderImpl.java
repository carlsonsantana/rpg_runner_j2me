package org.rpgrunner.game.j2me;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapRender;
import org.rpgrunner.game.map.Layer;

public class MapRenderImpl implements MapRender {
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final int screenWidth;
    private final int screenHeight;

    public MapRenderImpl(
        final Graphics gameGraphics,
        final Map map,
        final int deviceScreenWidth,
        final int deviceScreenHeight
    ) {
        graphics = gameGraphics;
        screenWidth = deviceScreenWidth;
        screenHeight = deviceScreenHeight;

        layerManager = new LayerManager();
        setTiledLayers(map);
    }

    private void setTiledLayers(final Map map) {
        Layer[] layers = map.getLayers();

        for (int i = layers.length - 1; i >= 0; i--) {
            layerManager.append(generateTiledLayer(layers[i]));
        }
    }

    private static TiledLayer generateTiledLayer(final Layer layer) {
        Image tileSetImage = TileSetRender.loadImage(layer.getTileSet());

        TiledLayer tiledLayer = new TiledLayer(
            layer.getWidth(),
            layer.getHeight(),
            tileSetImage,
            TileSetRender.TILE_WIDTH,
            TileSetRender.TILE_HEIGHT
        );
        fillTiledlayer(layer, tiledLayer);

        return tiledLayer;
    }

    private static void fillTiledlayer(
        final Layer layer,
        final TiledLayer tiledLayer
    ) {
        int height = layer.getHeight();
        int width = layer.getWidth();
        byte[][] tileMap = layer.getTileMap();
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

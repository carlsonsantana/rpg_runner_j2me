package org.rpgrunner.j2me;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.j2me.tileset.TileSetRender;
import org.rpgrunner.map.Layer;
import org.rpgrunner.map.Map;

public class MapRender {
    private final TiledLayer[] tiledLayers;

    public MapRender(final Map map) {
        tiledLayers = getTiledLayersFromMap(map);
    }

    private static TiledLayer[] getTiledLayersFromMap(final Map map) {
        Layer[] layers = map.getLayers();
        TiledLayer[] mapTiledLayers = new TiledLayer[layers.length];

        for (int i = 0; i < layers.length; i++) {
            mapTiledLayers[i] = generateTiledLayer(layers[i]);
        }

        return mapTiledLayers;
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

    public TiledLayer[] getTiledLayers() {
        return tiledLayers;
    }
}

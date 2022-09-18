package org.rpgrunner.j2me.map;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

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
        int width = layer.getWidth();
        int height = layer.getHeight();
        byte[] tileMap = layer.getTileMap();

        for (int row = 0; row < height; row++) {
            for (int cell = 0; cell < width; cell++) {
                int position = (row * width) + cell;

                if (tileMap[position] > 0) {
                    tiledLayer.setCell(cell, row, tileMap[position]);
                }
            }
        }
    }

    public TiledLayer[] getTiledLayers() {
        return tiledLayers;
    }
}

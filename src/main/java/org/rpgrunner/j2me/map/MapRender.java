package org.rpgrunner.j2me.map;

import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.map.Layer;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;
import org.rpgrunner.map.TileSet;

public class MapRender {
    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;
    private static final String TILESET_IMAGE_EXTENSION = ".png";
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
        Image tileSetImage = loadImage(layer.getTileSet());

        TiledLayer tiledLayer = new TiledLayer(
            layer.getWidth(),
            layer.getHeight(),
            tileSetImage,
            TILE_WIDTH,
            TILE_HEIGHT
        );
        fillTiledlayer(layer, tiledLayer);

        return tiledLayer;
    }

    private static Image loadImage(final TileSet tileSet) {
        String tileSetFileName = (
            MapLoader.TILESET_DIRECTORY
            + tileSet.getID()
            + TILESET_IMAGE_EXTENSION
        );

        try {
            return Image.createImage(tileSetFileName);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
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

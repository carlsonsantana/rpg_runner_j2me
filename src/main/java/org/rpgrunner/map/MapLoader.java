package org.rpgrunner.map;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.MapAreaEventListener;
import org.rpgrunner.event.MapEvent;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.helper.Loader;
import org.rpgrunner.tileset.TileSet;
import org.rpgrunner.tileset.TileSetLoader;

public class MapLoader {
    private static final String MAPS_DIRECTORY = "/maps/";
    private static final String MAPS_EXTENSION = ".map";
    private final ActionAbstractFactory actionAbstractFactory;

    public MapLoader(final ActionAbstractFactory currentActionAbstractFactory) {
        actionAbstractFactory = currentActionAbstractFactory;
    }

    public Map loadMap(final String fileBaseName) {
        InputStream mapInputStream = loadFile(fileBaseName);

        try {
            return extractMap(fileBaseName, mapInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream loadFile(final String fileBaseName) {
        String filePath = MAPS_DIRECTORY + fileBaseName + MAPS_EXTENSION;

        return MapLoader.class.getResourceAsStream(filePath);
    }

    private Map extractMap(
        final String fileBaseName,
        final InputStream mapInputStream
    ) throws IOException {
        int width = mapInputStream.read();
        int height = mapInputStream.read();

        int layersSize = mapInputStream.read();
        Layer[] layers = new Layer[layersSize];

        for (int i = 0; i < layersSize; i++) {
            layers[i] = extractLayer(mapInputStream, width, height);
        }

        Action action = actionAbstractFactory.create(mapInputStream);
        MapAreaEventListener[] mapAreaEventListeners = (
            extractMapAreaEventListeners(mapInputStream)
        );

        mapInputStream.close();

        return new Map(fileBaseName, layers, action, mapAreaEventListeners);
    }

    private Layer extractLayer(
        final InputStream mapInputStream,
        final int width,
        final int height
    ) throws IOException {
        String tileSetFileName = Loader.extractString(mapInputStream);
        TileSet tileSet = TileSetLoader.loadTileSet(tileSetFileName);

        byte[][] tileMap = extractTileMap(mapInputStream, width, height);

        return new Layer(tileSet, tileMap);
    }

    private byte[][] extractTileMap(
        final InputStream mapInputStream,
        final int width,
        final int height
    ) throws IOException {
        byte[][] tileMap = new byte[height][width];

        for (int i = 0; i < height; i++) {
            mapInputStream.read(tileMap[i]);
        }

        return tileMap;
    }

    private MapAreaEventListener[] extractMapAreaEventListeners(
        final InputStream mapInputStream
    ) throws IOException {
        int length = mapInputStream.read();
        MapAreaEventListener[] mapAreaEventListeners = (
            new MapAreaEventListener[length]
        );

        for (int i = 0; i < length; i++) {
            mapAreaEventListeners[i] = extractEventListener(mapInputStream);
        }

        return mapAreaEventListeners;
    }

    private MapAreaEventListener extractEventListener(
        final InputStream mapInputStream
    ) throws IOException {
        int tilePositionX = mapInputStream.read();
        int tilePositionY = mapInputStream.read();
        int tilesWidth = mapInputStream.read();
        int tilesHeight = mapInputStream.read();
        MapEvent mapEvent = extractMapEvent(mapInputStream);

        return new MapAreaEventListener(
            tilePositionX,
            tilePositionY,
            tilesWidth,
            tilesHeight,
            mapEvent
        );
    }

    private MapEvent extractMapEvent(
        final InputStream mapInputStream
    ) throws IOException {
        byte directions = (byte) mapInputStream.read();
        Action action = actionAbstractFactory.create(mapInputStream);

        return new MapEvent(action, directions);
    }
}

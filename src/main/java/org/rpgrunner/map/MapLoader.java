package org.rpgrunner.map;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.MapEventArea;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;

public class MapLoader {
    private static final String MAPS_DIRECTORY = "/maps/";
    private static final String MAPS_EXTENSION = ".map";
    public static final String TILESET_DIRECTORY = "/tilesets/";
    private static final String TILESET_EXTENSION = ".tileset";
    private final ActionAbstractFactory actionAbstractFactory;

    public MapLoader(final ActionAbstractFactory currentActionAbstractFactory) {
        actionAbstractFactory = currentActionAbstractFactory;
    }

    public Map loadMap(final byte id) {
        InputStream mapInputStream = loadFile(id);

        try {
            return extractMap(id, mapInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream loadFile(final byte mapID) {
        String filePath = MAPS_DIRECTORY + mapID + MAPS_EXTENSION;

        return MapLoader.class.getResourceAsStream(filePath);
    }

    private Map extractMap(
        final byte id,
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
        MapEventArea[] mapEventAreas = extractMapEventAreas(mapInputStream);

        mapInputStream.close();

        return new Map(id, layers, action, mapEventAreas);
    }

    private Layer extractLayer(
        final InputStream mapInputStream,
        final int width,
        final int height
    ) throws IOException {
        byte tileSetID = (byte) mapInputStream.read();
        TileSet tileSet = loadTileSet(tileSetID);

        byte[] tileMap = extractTileMap(mapInputStream, width, height);

        return new Layer(tileSet, tileMap, width, height);
    }

    private byte[] extractTileMap(
        final InputStream mapInputStream,
        final int width,
        final int height
    ) throws IOException {
        byte[] tileMap = new byte[height * width];

        mapInputStream.read(tileMap);

        return tileMap;
    }

    private MapEventArea[] extractMapEventAreas(
        final InputStream mapInputStream
    ) throws IOException {
        int length = mapInputStream.read();
        MapEventArea[] mapEventAreas = new MapEventArea[length];

        for (int i = 0; i < length; i++) {
            mapEventAreas[i] = extractMapEventArea(mapInputStream);
        }

        return mapEventAreas;
    }

    private MapEventArea extractMapEventArea(
        final InputStream mapInputStream
    ) throws IOException {
        int tilePositionX = mapInputStream.read();
        int tilePositionY = mapInputStream.read();
        int tilesWidth = mapInputStream.read();
        int tilesHeight = mapInputStream.read();
        byte directions = (byte) mapInputStream.read();
        Action action = actionAbstractFactory.create(mapInputStream);

        return new MapEventArea(
            tilePositionX,
            tilePositionY,
            tilesWidth,
            tilesHeight,
            directions,
            action
        );
    }

    private static TileSet loadTileSet(final byte tyleSetID) {
        InputStream tileSetInputStream = loadTyleSetFile(tyleSetID);

        try {
            return extractTileSet(tyleSetID, tileSetInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private static InputStream loadTyleSetFile(final byte tyleSetID) {
        String filePath = TILESET_DIRECTORY + tyleSetID + TILESET_EXTENSION;

        return MapLoader.class.getResourceAsStream(filePath);
    }

    private static TileSet extractTileSet(
        final byte tyleSetID,
        final InputStream tileSetInputStream
    ) throws IOException {
        int collisionsSize = tileSetInputStream.read() + 1;
        int collisionsByteSize = (collisionsSize / 2) + (collisionsSize % 2);
        byte[] collisions = new byte[collisionsByteSize];

        tileSetInputStream.read(collisions);
        tileSetInputStream.close();

        return new TileSet(tyleSetID, collisions);
    }
}

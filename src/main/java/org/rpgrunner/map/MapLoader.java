package org.rpgrunner.map;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.tileset.TileSet;
import org.rpgrunner.tileset.TileSetLoader;

public final class MapLoader {
    private static final String MAPS_DIRECTORY = "/maps/";
    private static final String MAPS_EXTENSION = ".map";

    private MapLoader() { }

    public static Map loadMap(final String fileBaseName) {
        InputStream mapInputStream = loadFile(fileBaseName);

        try {
            return extractMap(fileBaseName, mapInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private static InputStream loadFile(final String fileBaseName) {
        String filePath = MAPS_DIRECTORY + fileBaseName + MAPS_EXTENSION;
        return MapLoader.class.getResourceAsStream(filePath);
    }

    private static Map extractMap(
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

        mapInputStream.close();

        return new Map(fileBaseName, layers);
    }

    private static Layer extractLayer(
        final InputStream mapInputStream,
        final int width,
        final int height
    ) throws IOException {
        String tileSetFileName = extractFileName(mapInputStream);
        TileSet tileSet = TileSetLoader.loadTileSet(tileSetFileName);

        byte[][] tileMap = extractTileMap(mapInputStream, width, height);

        return new Layer(tileSet, tileMap);
    }

    private static String extractFileName(
        final InputStream mapInputStream
    ) throws IOException {
        int lengthString = mapInputStream.read();
        byte[] stringBytes = new byte[lengthString];
        mapInputStream.read(stringBytes);
        return new String(stringBytes);
    }

    private static byte[][] extractTileMap(
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
}

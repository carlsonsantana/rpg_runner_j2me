package org.rpgrunner.tileset;

import java.io.IOException;
import java.io.InputStream;

public class TileSetLoader {
    public static final String TILESET_DIRECTORY = "/tilesets/";
    private static final String TILESET_EXTENSION = ".tileset";

    private TileSetLoader() { }

    public static TileSet loadTileSet(final String fileBaseName) {
        InputStream tileSetInputStream = loadFile(fileBaseName);

        try {
            return extractTileSet(fileBaseName, tileSetInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private static InputStream loadFile(final String fileBaseName) {
        String filePath = TILESET_DIRECTORY + fileBaseName + TILESET_EXTENSION;

        return TileSetLoader.class.getResourceAsStream(filePath);
    }

    private static TileSet extractTileSet(
        final String fileBaseName,
        final InputStream tileSetInputStream
    ) throws IOException {
        int collisionsSize = tileSetInputStream.read() + 1;
        int collisionsByteSize = (collisionsSize / 2) + (collisionsSize % 2);
        byte[] collisions = new byte[collisionsByteSize];

        tileSetInputStream.read(collisions);
        tileSetInputStream.close();

        return new TileSet(fileBaseName, collisions);
    }
}

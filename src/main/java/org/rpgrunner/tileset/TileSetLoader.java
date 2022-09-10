package org.rpgrunner.tileset;

import java.io.IOException;
import java.io.InputStream;

public class TileSetLoader {
    public static final String TILESET_DIRECTORY = "/tilesets/";
    private static final String TILESET_EXTENSION = ".tileset";

    private TileSetLoader() { }

    public static TileSet loadTileSet(final byte tyleSetID) {
        InputStream tileSetInputStream = loadFile(tyleSetID);

        try {
            return extractTileSet(tyleSetID, tileSetInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private static InputStream loadFile(final byte tyleSetID) {
        String filePath = TILESET_DIRECTORY + tyleSetID + TILESET_EXTENSION;

        return TileSetLoader.class.getResourceAsStream(filePath);
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

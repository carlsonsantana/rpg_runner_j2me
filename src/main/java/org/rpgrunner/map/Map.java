package org.rpgrunner.map;

public class Map {
    private final String fileBaseName;
    private final Layer[] layers;

    public Map(final String mapFileBaseName, final Layer[] mapLayers) {
        fileBaseName = mapFileBaseName;
        layers = mapLayers;
    }

    public String getFileBaseName() {
        return fileBaseName;
    }

    public int getWidth() {
        return layers[0].getWidth();
    }

    public int getHeight() {
        return layers[0].getHeight();
    }

    public Layer[] getLayers() {
        return layers;
    }

    public boolean canMoveTo(
        final int fromX,
        final int fromY,
        final int toX,
        final int toY,
        final byte direction
    ) {
        for (int i = 0; i < layers.length; i++) {
            if (!layers[i].canMoveTo(fromX, fromY, toX, toY, direction)) {
                return false;
            }
        }

        return true;
    }
}

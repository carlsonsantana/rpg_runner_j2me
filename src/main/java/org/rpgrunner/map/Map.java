package org.rpgrunner.map;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.MapEventArea;
import org.rpgrunner.event.action.Action;

public class Map {
    private final String fileBaseName;
    private final Layer[] layers;
    private final Action startAction;
    private final MapEventArea[] mapEventAreas;

    public Map(
        final String mapFileBaseName,
        final Layer[] mapLayers,
        final Action mapStartAction,
        final MapEventArea[] currentMapEventAreas
    ) {
        fileBaseName = mapFileBaseName;
        layers = mapLayers;
        startAction = mapStartAction;
        mapEventAreas = currentMapEventAreas;
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

    public boolean canMove(final GameCharacter character) {
        for (int i = 0; i < layers.length; i++) {
            if (!layers[i].canMove(character)) {
                return false;
            }
        }

        return true;
    }

    public Action getStartAction() {
        return startAction;
    }

    public MapEventArea[] getMapEventAreas() {
        return mapEventAreas;
    }
}

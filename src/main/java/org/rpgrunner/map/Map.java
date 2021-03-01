package org.rpgrunner.map;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;

public class Map {
    private final String fileBaseName;
    private final Layer[] layers;
    private final Action startAction;

    public Map(
        final String mapFileBaseName,
        final Layer[] mapLayers,
        final Action mapStartAction
    ) {
        fileBaseName = mapFileBaseName;
        layers = mapLayers;
        startAction = mapStartAction;
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
        int fromX = character.getMapPositionX();
        int fromY = character.getMapPositionY();
        int toX = character.getMapNextPositionX();
        int toY = character.getMapNextPositionY();
        byte direction = character.getDirection();

        for (int i = 0; i < layers.length; i++) {
            if (!layers[i].canMoveTo(fromX, fromY, toX, toY, direction)) {
                return false;
            }
        }

        return true;
    }

    public void executeStartActions() {
        startAction.execute();
    }
}

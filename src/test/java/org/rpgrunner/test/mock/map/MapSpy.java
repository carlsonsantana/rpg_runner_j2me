package org.rpgrunner.test.mock.map;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.event.MapEventArea;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.map.Layer;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class MapSpy extends Map {
    private final Action startAction;
    private boolean canMove;
    private int width;
    private int height;
    private MapEventArea[] mapEventAreas;

    public MapSpy() {
        super((byte) 0, null, null, null);
        width = 0;
        height = 0;
        startAction = new ActionSpy();
    }

    public void setWidth(final int newWidth) {
        width = newWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(final int newHeight) {
        height = newHeight;
    }

    public int getHeight() {
        return height;
    }

    public Layer[] getLayers() {
        return null;
    }

    public void setCanMoveTo(final boolean newCanMove) {
        canMove = newCanMove;
    }

    public boolean canMove(final CharacterAnimation character) {
        return canMove;
    }

    public Action getStartAction() {
        return startAction;
    }

    public void setMapEventAreas(final MapEventArea[] newMapEventAreas) {
        mapEventAreas = newMapEventAreas;
    }

    public MapEventArea[] getMapEventAreas() {
        return mapEventAreas;
    }
}

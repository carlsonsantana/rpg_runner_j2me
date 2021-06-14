package org.rpgrunner.test.mock.graphics;

import java.util.Vector;

import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.map.Map;

public class MapGraphicsRenderSpy extends GraphicsRenderSpy implements
    MapGraphicsRender {
    private Map map;
    private Vector characterElements;

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }

    public void setCharacterElements(final Vector newCharacterElements) {
        characterElements = newCharacterElements;
    }

    public Vector getCharacterElements() {
        return characterElements;
    }
}

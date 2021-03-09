package org.rpgrunner.test.mock.graphics;

import java.util.Vector;

import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.map.Map;

public class GraphicsRenderSpy implements GraphicsRender {
    private Map map;
    private Vector characterElements;
    private boolean renderCalled;

    public GraphicsRenderSpy() {
        renderCalled = false;
    }

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

    public void showMessage(final String message) { }

    public void render() {
        renderCalled = true;
    }

    public boolean isRenderCalled() {
        return renderCalled;
    }
}

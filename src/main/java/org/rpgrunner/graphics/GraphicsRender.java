package org.rpgrunner.graphics;

import java.util.Vector;

import org.rpgrunner.map.Map;

public interface GraphicsRender {
    void setMap(Map map);
    void setCharacterElements(Vector characterElements);
    void showMessage(String message);
    void render();
}

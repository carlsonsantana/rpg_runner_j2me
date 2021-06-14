package org.rpgrunner.graphics;

import java.util.Vector;

import org.rpgrunner.map.Map;

public interface MapGraphicsRender extends GraphicsRender {
    void setMap(Map map);
    void setCharacterElements(Vector characterElements);
}

package org.rpgrunner.graphics;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.map.Map;

public interface GraphicsRender {
    void setMap(Map map);
    void setCharacterElements(CharacterElement[] characterElements);
    void render();
}

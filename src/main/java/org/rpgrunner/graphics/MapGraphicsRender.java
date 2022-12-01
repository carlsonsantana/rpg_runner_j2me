package org.rpgrunner.graphics;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.map.Map;

public interface MapGraphicsRender extends GraphicsRender {
    void setMap(Map map);
    void notifyChangesCharacterElements();
    void setCharacterAnimation(GameCharacter characterAnimation);
}

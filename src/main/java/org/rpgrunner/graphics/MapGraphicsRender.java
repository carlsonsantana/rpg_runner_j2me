package org.rpgrunner.graphics;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.map.Map;

public interface MapGraphicsRender extends GraphicsRender {
    void setMap(Map map);
    void notifyChangesCharacterElements();
    void setCharacterAnimation(CharacterAnimation characterAnimation);
}

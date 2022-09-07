package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.map.Map;

public class MapGraphicsRenderSpy extends GraphicsRenderSpy implements
MapGraphicsRender {
    private Map map;
    private CharacterAnimation characterAnimation;

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }

    public void notifyChangesCharacterElements() { }

    public void setCharacterAnimation(
        final CharacterAnimation newCharacterAnimation
    ) {
        characterAnimation = newCharacterAnimation;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }
}

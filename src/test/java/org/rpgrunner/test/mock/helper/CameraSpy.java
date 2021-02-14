package org.rpgrunner.test.mock.helper;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.map.Map;

public class CameraSpy extends Camera {
    private Map map;
    private CharacterAnimation characterAnimation;

    public CameraSpy() {
        super(0, 0);
    }

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }

    public void setCharacterAnimation(
        final CharacterAnimation newCharacterAnimation
    ) {
        characterAnimation = newCharacterAnimation;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }
}

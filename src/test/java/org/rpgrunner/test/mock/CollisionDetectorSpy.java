package org.rpgrunner.test.mock;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.helper.CollisionDetector;

public class CollisionDetectorSpy extends CollisionDetector {
    public void setMap(final Map map) {
        throw new RuntimeException();
    }

    public void setCharacters(final GameCharacter[] characters) {
        throw new RuntimeException();
    }
}

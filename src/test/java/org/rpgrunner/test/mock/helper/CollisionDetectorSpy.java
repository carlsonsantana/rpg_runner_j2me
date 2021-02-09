package org.rpgrunner.test.mock.helper;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.map.Map;

public class CollisionDetectorSpy extends CollisionDetector {
    private boolean canMoveValue;

    public CollisionDetectorSpy() {
        canMoveValue = true;
    }

    public void setMap(final Map map) {
        throw new RuntimeException();
    }

    public void setCharacters(final GameCharacter[] characters) {
        throw new RuntimeException();
    }

    public void setCanMove(final boolean newCanMoveValue) {
        canMoveValue = newCanMoveValue;
    }

    public boolean canMove(final GameCharacter character) {
        return canMoveValue;
    }
}
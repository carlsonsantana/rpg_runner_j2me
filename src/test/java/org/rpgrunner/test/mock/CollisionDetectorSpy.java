package org.rpgrunner.test.mock;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.helper.CollisionDetector;

public class CollisionDetectorSpy extends CollisionDetector {
    private boolean canMoveValue;

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

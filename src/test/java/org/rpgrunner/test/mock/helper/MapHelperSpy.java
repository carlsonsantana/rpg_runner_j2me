package org.rpgrunner.test.mock.helper;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.GameControllerSpy;

public class MapHelperSpy extends MapHelper {
    private boolean canMoveValue;

    public MapHelperSpy() {
        super(new GameControllerSpy());
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

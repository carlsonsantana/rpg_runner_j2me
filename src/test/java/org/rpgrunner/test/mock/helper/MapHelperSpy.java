package org.rpgrunner.test.mock.helper;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class MapHelperSpy extends MapHelper {
    private Map currentMap;
    private boolean canMoveValue;
    private boolean executeInteractActionCalled;

    public MapHelperSpy() {
        super(null);
        canMoveValue = true;
        executeInteractActionCalled = false;
    }

    public void setMap(final Map map) {
        currentMap = map;
    }

    public Map getCurrentMap() {
        return currentMap;
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

    public void executeInteractAction(final GameCharacter playerCharacter) {
        executeInteractActionCalled = true;
    }

    public boolean isExecuteInteractActionCalled() {
        return executeInteractActionCalled;
    }

    public void resetExecuteInteractActionCalled() {
        executeInteractActionCalled = false;
    }
}

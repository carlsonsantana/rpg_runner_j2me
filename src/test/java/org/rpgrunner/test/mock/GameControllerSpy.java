package org.rpgrunner.test.mock;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class GameControllerSpy extends GameController {
    private Map map;
    private int countMapChanged;
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private ActionAbstractFactorySpy actionAbstractFactory;
    private Action lastAction;

    public GameControllerSpy() {
        super(null, null);
        map = null;
        countMapChanged = 0;
        actionAbstractFactory = new ActionAbstractFactorySpy();
    }

    public void setMap(final Map newMap) {
        map = newMap;
        countMapChanged++;
    }

    public Map getMap() {
        return map;
    }

    public int getCountMapChanged() {
        return countMapChanged;
    }

    public void setPlayerCharacterElement(
        final CharacterElement newPlayerCharacterElement
    ) {
        playerCharacterElement = newPlayerCharacterElement;
    }

    public CharacterElement getPlayerCharacterElement() {
        return playerCharacterElement;
    }

    public MapHelper getMapHelper() {
        return new MapHelper(this);
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        lastCharacterElementAdded = characterElement;
    }

    public CharacterElement getLastCharacterElementAdded() {
        return lastCharacterElementAdded;
    }

    public void executeAction(final Action newAction) {
        lastAction = newAction;
    }

    public Action getExecutedAction() {
        return lastAction;
    }
}

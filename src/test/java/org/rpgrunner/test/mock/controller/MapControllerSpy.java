package org.rpgrunner.test.mock.controller;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class MapControllerSpy extends MapController {
    private Map map;
    private int countMapChanged;
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private Action lastAction;
    private String lastMessage;

    public MapControllerSpy() {
        super(null, null, new MapHelperSpy());
        map = null;
        countMapChanged = 0;
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
        return new MapHelper(null);
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

    public void showMessage(final String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

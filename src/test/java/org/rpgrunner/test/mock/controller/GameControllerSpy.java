package org.rpgrunner.test.mock.controller;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class GameControllerSpy extends GameController {
    private final MapController mapController;
    private Map map;
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private Action lastAction;
    private String lastMessage;

    public GameControllerSpy() {
        super(null, null, null);
        mapController = new MapControllerSpy(this);
        map = null;
    }

    public MapHelper getMapHelper() {
        return new MapHelper(this, null);
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

    public MapController getMapController() {
        return mapController;
    }
}
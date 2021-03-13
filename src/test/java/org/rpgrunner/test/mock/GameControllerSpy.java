package org.rpgrunner.test.mock;

import org.rpgrunner.GameController;
import org.rpgrunner.MapController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class GameControllerSpy extends GameController {
    private final MapController mapController;
    private Map map;
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private ActionAbstractFactorySpy actionAbstractFactory;
    private Action lastAction;
    private String lastMessage;

    public GameControllerSpy() {
        super(null, null);
        mapController = new MapControllerSpy(this);
        map = null;
        actionAbstractFactory = new ActionAbstractFactorySpy();
    }

    public MapHelper getMapHelper() {
        return new MapHelper(this);
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

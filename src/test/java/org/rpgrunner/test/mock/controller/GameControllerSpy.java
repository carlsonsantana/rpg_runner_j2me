package org.rpgrunner.test.mock.controller;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;

public class GameControllerSpy extends GameController {
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private String lastMessage;

    public GameControllerSpy() {
        this(new MapControllerSpy());
    }

    public GameControllerSpy(final MapController mapController) {
        super(null, mapController);
    }

    public void showMessage(final String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

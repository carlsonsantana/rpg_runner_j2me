package org.rpgrunner.test.mock.controller;

import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;

public class GameControllerSpy extends GameController {
    private String lastMessage;

    public GameControllerSpy() {
        this(new MapControllerSpy());
    }

    public GameControllerSpy(final MapController mapController) {
        super(mapController, null);
    }

    public void showMessage(final String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

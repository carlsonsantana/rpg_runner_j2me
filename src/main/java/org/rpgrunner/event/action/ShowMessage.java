package org.rpgrunner.event.action;

import org.rpgrunner.controller.GameController;

public class ShowMessage implements Action {
    private final GameController gameController;
    private final String message;

    public ShowMessage(
        final GameController currentGameController,
        final String messageToShow
    ) {
        gameController = currentGameController;
        message = messageToShow;
    }

    public void execute() {
        gameController.showMessage(message);
    }
}

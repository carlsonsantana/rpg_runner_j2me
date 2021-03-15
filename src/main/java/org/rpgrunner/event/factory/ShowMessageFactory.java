package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.ShowMessage;
import org.rpgrunner.helper.Loader;

public class ShowMessageFactory implements ActionFactory {
    private final GameController gameController;

    public ShowMessageFactory(final GameController currentGameController) {
        gameController = currentGameController;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String message = Loader.extractString(inputStream);
        ShowMessage showMessage = new ShowMessage(
            gameController,
            message
        );

        return showMessage;
    }
}

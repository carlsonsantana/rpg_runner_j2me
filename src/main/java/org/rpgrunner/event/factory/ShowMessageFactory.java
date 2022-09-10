package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.ShowMessage;

public class ShowMessageFactory implements IdentifiedActionFactory {
    private static final int ID_VALUE = 6;
    private final GameController gameController;

    public ShowMessageFactory(final GameController currentGameController) {
        gameController = currentGameController;
    }

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String message = extractString(inputStream);
        ShowMessage showMessage = new ShowMessage(
            gameController,
            message
        );

        return showMessage;
    }

    private String extractString(
        final InputStream inputStream
    ) throws IOException {
        int lengthString = inputStream.read();
        byte[] stringBytes = new byte[lengthString];
        inputStream.read(stringBytes);

        return new String(stringBytes);
    }
}

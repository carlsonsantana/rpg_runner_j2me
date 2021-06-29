package org.rpgrunner.event;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;

public class GameStartEvent {
    private static final String GAME_FILE_NAME = "/start.event";

    public void execute(final ActionAbstractFactory actionAbstractFactory) {
        InputStream inputStream = load();
        try {
            Action action = actionAbstractFactory.create(inputStream);
            action.execute();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }

    private InputStream load() {
        return getClass().getResourceAsStream(GAME_FILE_NAME);
    }
}

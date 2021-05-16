package org.rpgrunner.test.mock.controller;

import org.rpgrunner.controller.MessageController;

public class MessageControllerSpy implements MessageController {
    private String lastMessage;

    public void pressKey(final int key) { }

    public void releaseKey(final int key) { }

    public void showMessage(final String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

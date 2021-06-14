package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageGraphicsRenderSpy extends GraphicsRenderSpy implements
    MessageGraphicsRender {
    private String lastMessage;
    private boolean showingMessage;

    public void showMessage(final String message) {
        lastMessage = message;
        showingMessage = true;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void hideMessage() {
        showingMessage = false;
    }

    public boolean isShowingMessage() {
        return showingMessage;
    }
}

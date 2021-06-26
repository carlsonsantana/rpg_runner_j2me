package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageGraphicsRenderSpy extends GraphicsRenderSpy implements
    MessageGraphicsRender {
    private String lastMessage;
    private boolean showingMessage;
    private boolean scrollUpCalled;
    private boolean scrollDownCalled;

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

    public void clearScroll() {
        scrollUpCalled = false;
        scrollDownCalled = false;
    }

    public void scrollUp() {
        scrollUpCalled = true;
    }

    public boolean isScrollUpCalled() {
        return scrollUpCalled;
    }

    public void scrollDown() {
        scrollDownCalled = true;
    }

    public boolean isScrollDownCalled() {
        return scrollDownCalled;
    }
}

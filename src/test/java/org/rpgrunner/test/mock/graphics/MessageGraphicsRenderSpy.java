package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageGraphicsRenderSpy extends GraphicsRenderSpy implements
MessageGraphicsRender {
    private String lastMessage;
    private boolean showingMessage;
    private boolean pageUpCalled;
    private boolean pageDownCalled;

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

    public void clearPage() {
        pageUpCalled = false;
        pageDownCalled = false;
    }

    public void pageUp() {
        pageUpCalled = true;
    }

    public boolean isPageUpCalled() {
        return pageUpCalled;
    }

    public void pageDown() {
        pageDownCalled = true;
    }

    public boolean isPageDownCalled() {
        return pageDownCalled;
    }
}

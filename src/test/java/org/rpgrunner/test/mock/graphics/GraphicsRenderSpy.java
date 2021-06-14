package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.graphics.GraphicsRender;

public class GraphicsRenderSpy implements GraphicsRender {
    private boolean renderCalled;
    private String lastMessage;
    private boolean showingMessage;

    public GraphicsRenderSpy() {
        renderCalled = false;
        showingMessage = false;
    }

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

    public void render() {
        renderCalled = true;
    }

    public boolean isRenderCalled() {
        return renderCalled;
    }
}

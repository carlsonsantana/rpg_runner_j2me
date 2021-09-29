package org.rpgrunner.test.mock.controller;

import org.rpgrunner.controller.MessageController;

public class MessageControllerSpy extends MessageController {
    private String lastMessage;
    private boolean finished;
    private boolean prepareFrameAnimationCalled;
    private boolean renderCalled;

    public MessageControllerSpy() {
        super(null, null);
    }

    public void showMessage(final String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public boolean isFinished() {
        return finished;
    }

    public void finish() {
        finished = true;
    }

    public void prepareFrameAnimation() {
        prepareFrameAnimationCalled = true;
    }

    public boolean isPrepareFrameAnimationCalled() {
        return prepareFrameAnimationCalled;
    }

    public void render() {
        renderCalled = true;
    }

    public boolean isRenderCalled() {
        return renderCalled;
    }
}

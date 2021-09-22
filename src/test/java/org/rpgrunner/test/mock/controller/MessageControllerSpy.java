package org.rpgrunner.test.mock.controller;

import org.rpgrunner.controller.MessageController;

public class MessageControllerSpy extends MessageController {
    private String lastMessage;
    private int pressedKey;
    private int releasedKey;
    private boolean finished;
    private boolean prepareFrameAnimationCalled;
    private boolean renderCalled;

    public MessageControllerSpy() {
        super(null, null);
    }

    public void pressKey(final int key) {
        pressedKey = key;
    }

    public int getPressedKey() {
        return pressedKey;
    }

    public void releaseKey(final int key) {
        releasedKey = key;
    }

    public int getReleasedKey() {
        return releasedKey;
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

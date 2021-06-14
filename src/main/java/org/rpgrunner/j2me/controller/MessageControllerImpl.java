package org.rpgrunner.j2me.controller;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.controller.MessageController;
import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageControllerImpl implements MessageController {
    private final MessageGraphicsRender messageGraphicsRender;
    private boolean finished;

    public MessageControllerImpl(
        final MessageGraphicsRender gameGraphicsRender
    ) {
        messageGraphicsRender = gameGraphicsRender;
        finished = true;
    }

    public void pressKey(final int key) { }

    public void releaseKey(final int key) {
        if (
            (key == GameCanvas.FIRE)
            || (key == GameCanvas.KEY_NUM5)
        ) {
            messageGraphicsRender.hideMessage();
            finished = true;
        }
    }

    public void prepareFrameAnimation() { }

    public void render() {
        messageGraphicsRender.render();
    }

    public void showMessage(final String message) {
        messageGraphicsRender.showMessage(message);
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }
}

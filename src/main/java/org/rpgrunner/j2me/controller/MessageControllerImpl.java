package org.rpgrunner.j2me.controller;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.controller.MessageController;
import org.rpgrunner.graphics.GraphicsRender;

public class MessageControllerImpl implements MessageController {
    private final GraphicsRender graphicsRender;
    private boolean finished;

    public MessageControllerImpl(final GraphicsRender gameGraphicsRender) {
        graphicsRender = gameGraphicsRender;
        finished = true;
    }

    public void pressKey(final int key) { }

    public void releaseKey(final int key) {
        if (
            (key == GameCanvas.FIRE)
            || (key == GameCanvas.KEY_NUM5)
        ) {
            graphicsRender.hideMessage();
            finished = true;
        }
    }

    public void prepareFrameAnimation() { }

    public void render() {
        graphicsRender.render();
    }

    public void showMessage(final String message) {
        graphicsRender.showMessage(message);
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }
}

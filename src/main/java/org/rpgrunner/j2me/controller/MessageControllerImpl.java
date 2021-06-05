package org.rpgrunner.j2me.controller;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.controller.MessageController;
import org.rpgrunner.graphics.GraphicsRender;

public class MessageControllerImpl implements MessageController {
    private final GraphicsRender graphicsRender;

    public MessageControllerImpl(final GraphicsRender gameGraphicsRender) {
        graphicsRender = gameGraphicsRender;
    }

    public void pressKey(final int key) { }

    public void releaseKey(final int key) {
        if (
            (key == GameCanvas.FIRE)
            || (key == GameCanvas.KEY_NUM5)
        ) {
            graphicsRender.hideMessage();
        }
    }

    public void showMessage(final String message) {
        graphicsRender.showMessage(message);
    }

    public boolean isFinished() {
        return false;
    }
}

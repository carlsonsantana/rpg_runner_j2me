package org.rpgrunner.j2me.controller;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.Direction;
import org.rpgrunner.controller.MessageController;
import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageControllerImpl implements MessageController {
    private final MessageGraphicsRender messageGraphicsRender;
    private boolean finished;
    private byte direction;

    public MessageControllerImpl(
        final MessageGraphicsRender gameGraphicsRender
    ) {
        messageGraphicsRender = gameGraphicsRender;
        finished = true;
    }

    public void pressKey(final int key) {
        if ((key == GameCanvas.UP) || (key == GameCanvas.KEY_NUM2)) {
            direction = Direction.UP;
        } else if ((key == GameCanvas.DOWN) || (key == GameCanvas.KEY_NUM8)) {
            direction = Direction.DOWN;
        }
    }

    public void releaseKey(final int key) {
        if (
            (key == GameCanvas.FIRE)
            || (key == GameCanvas.KEY_NUM5)
        ) {
            messageGraphicsRender.hideMessage();
            finished = true;
        } else if (
            (Direction.isUp(direction))
            && (
                (key == GameCanvas.UP)
                || (key == GameCanvas.KEY_NUM2)
            )
        ) {
            direction = Direction.NO_DIRECTION;
        } else if (
            (Direction.isDown(direction))
            && (
                (key == GameCanvas.DOWN)
                || (key == GameCanvas.KEY_NUM8)
            )
        ) {
            direction = Direction.NO_DIRECTION;
        }
    }

    public void prepareFrameAnimation() {
        if (Direction.isUp(direction)) {
            messageGraphicsRender.scrollUp();
        } else if (Direction.isDown(direction)) {
            messageGraphicsRender.scrollDown();
        }
    }

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

package org.rpgrunner.j2me.controller;

import org.rpgrunner.Direction;
import org.rpgrunner.controller.MessageController;
import org.rpgrunner.graphics.MessageGraphicsRender;
import org.rpgrunner.j2me.Key;

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
        if (Key.isUp(key)) {
            direction = Direction.UP;
        } else if (Key.isDown(key)) {
            direction = Direction.DOWN;
        }
    }

    public void releaseKey(final int key) {
        if (Key.isAction(key)) {
            messageGraphicsRender.hideMessage();
            finished = true;
        } else if ((Direction.isUp(direction)) && (Key.isUp(key))) {
            direction = Direction.NO_DIRECTION;
        } else if ((Direction.isDown(direction)) && (Key.isDown(key))) {
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

package org.rpgrunner.controller;

import org.rpgrunner.graphics.MessageGraphicsRender;
import org.rpgrunner.helper.Input;

public class MessageController implements Controller {
    private final MessageGraphicsRender messageGraphicsRender;
    private final Input input;
    private boolean finished;

    public MessageController(
        final MessageGraphicsRender gameGraphicsRender,
        final Input currentInput
    ) {
        messageGraphicsRender = gameGraphicsRender;
        input = currentInput;
        finished = true;
    }

    public void pressKey(final int key) { }

    public void releaseKey(final int key) { }

    public void prepareFrameAnimation() {
        if (input.isHoldingUp()) {
            messageGraphicsRender.scrollUp();
        } else if (input.isHoldingDown()) {
            messageGraphicsRender.scrollDown();
        } else if (input.isActionPressed()) {
            messageGraphicsRender.hideMessage();
            finished = true;
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

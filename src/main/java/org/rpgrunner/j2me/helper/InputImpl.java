package org.rpgrunner.j2me.helper;

import org.rpgrunner.helper.Input;
import org.rpgrunner.j2me.Key;

public class InputImpl implements Input {
    private boolean actionPressed;
    private boolean holdingUp;
    private boolean holdingRight;
    private boolean holdingDown;
    private boolean holdingLeft;

    public boolean isActionPressed() {
        return actionPressed;
    }

    public boolean isHoldingUp() {
        return holdingUp;
    }

    public boolean isHoldingRight() {
        return holdingRight;
    }

    public boolean isHoldingDown() {
        return holdingDown;
    }

    public boolean isHoldingLeft() {
        return holdingLeft;
    }

    public void pressKey(final int key) {
        if (Key.isUp(key)) {
            holdingUp = true;
        } else if (Key.isRight(key)) {
            holdingRight = true;
        } else if (Key.isDown(key)) {
            holdingDown = true;
        } else if (Key.isLeft(key)) {
            holdingLeft = true;
        }
    }

    public void releaseKey(final int key) {
        if (Key.isAction(key)) {
            actionPressed = true;
        } else if (Key.isUp(key)) {
            holdingUp = false;
        } else if (Key.isRight(key)) {
            holdingRight = false;
        } else if (Key.isDown(key)) {
            holdingDown = false;
        } else if (Key.isLeft(key)) {
            holdingLeft = false;
        }
    }

    public void update() {
        actionPressed = false;
    }
}

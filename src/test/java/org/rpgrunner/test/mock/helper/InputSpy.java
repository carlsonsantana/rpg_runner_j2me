package org.rpgrunner.test.mock.helper;

import org.rpgrunner.helper.Input;

public class InputSpy implements Input {
    private boolean actionPressed;
    private boolean holdingUp;
    private boolean holdingRight;
    private boolean holdingDown;
    private boolean holdingLeft;

    public void setActionPressed(final boolean newValueActionPressed) {
        actionPressed = newValueActionPressed;
    }

    public boolean isActionPressed() {
        return actionPressed;
    }

    public void setHoldingUp(final boolean newValueHoldingUp) {
        holdingUp = newValueHoldingUp;
    }

    public boolean isHoldingUp() {
        return holdingUp;
    }

    public void setHoldingRight(final boolean newValueHoldingRight) {
        holdingRight = newValueHoldingRight;
    }

    public boolean isHoldingRight() {
        return holdingRight;
    }

    public void setHoldingDown(final boolean newValueHoldingDown) {
        holdingDown = newValueHoldingDown;
    }

    public boolean isHoldingDown() {
        return holdingDown;
    }

    public void setHoldingLeft(final boolean newValueHoldingLeft) {
        holdingLeft = newValueHoldingLeft;
    }

    public boolean isHoldingLeft() {
        return holdingLeft;
    }
}

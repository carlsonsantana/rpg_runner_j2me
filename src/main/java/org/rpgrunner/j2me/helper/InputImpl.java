package org.rpgrunner.j2me.helper;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.helper.Input;

public class InputImpl implements Input {
    private static final int[] ACTION_KEYS = new int[] {
        GameCanvas.FIRE,
        GameCanvas.KEY_NUM5
    };
    private static final int[] UP_KEYS = new int[] {
        GameCanvas.UP,
        GameCanvas.KEY_NUM2
    };
    private static final int[] RIGHT_KEYS = new int[] {
        GameCanvas.RIGHT,
        GameCanvas.KEY_NUM6
    };
    private static final int[] DOWN_KEYS = new int[] {
        GameCanvas.DOWN,
        GameCanvas.KEY_NUM8
    };
    private static final int[] LEFT_KEYS = new int[] {
        GameCanvas.LEFT,
        GameCanvas.KEY_NUM4
    };
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
        if (isUp(key)) {
            holdingUp = true;
        } else if (isRight(key)) {
            holdingRight = true;
        } else if (isDown(key)) {
            holdingDown = true;
        } else if (isLeft(key)) {
            holdingLeft = true;
        }
    }

    public void releaseKey(final int key) {
        if (isAction(key)) {
            actionPressed = true;
        } else if (isUp(key)) {
            holdingUp = false;
        } else if (isRight(key)) {
            holdingRight = false;
        } else if (isDown(key)) {
            holdingDown = false;
        } else if (isLeft(key)) {
            holdingLeft = false;
        }
    }

    private boolean isAction(final int key) {
        return containsKey(ACTION_KEYS, key);
    }

    private boolean isUp(final int key) {
        return containsKey(UP_KEYS, key);
    }

    private boolean isRight(final int key) {
        return containsKey(RIGHT_KEYS, key);
    }

    private boolean isDown(final int key) {
        return containsKey(DOWN_KEYS, key);
    }

    private boolean isLeft(final int key) {
        return containsKey(LEFT_KEYS, key);
    }

    private boolean containsKey(final int[] keys, final int key) {
        return (keys[0] == key) || (keys[1] == key);
    }

    public void update() {
        actionPressed = false;
    }
}

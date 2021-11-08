package org.rpgrunner.j2me.helper;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;

public class InputImplTest extends TestCase {
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
    private InputImpl input;

    public void setUp() {
        input = new InputImpl();
    }

    public void testStartWithNoInput() {
        Assert.assertFalse(input.isActionPressed());
        Assert.assertFalse(input.isHoldingUp());
        Assert.assertFalse(input.isHoldingRight());
        Assert.assertFalse(input.isHoldingDown());
        Assert.assertFalse(input.isHoldingLeft());
    }

    public void testIsActionPressedAfterReleaseSameKey() {
        for (int i = 0, length = ACTION_KEYS.length; i < length; i++) {
            int key = ACTION_KEYS[i];

            checkIsActionPressedAfterReleaseSameKey(key);
        }
    }

    private void checkIsActionPressedAfterReleaseSameKey(final int key) {
        input.pressKey(key);

        Assert.assertFalse(input.isActionPressed());

        input.releaseKey(key);

        Assert.assertTrue(input.isActionPressed());

        input.update();

        Assert.assertFalse(input.isActionPressed());
    }

    public void testDoNotIsActionPressedAfterReleaseDiferentKey() {
        for (int i = 0, length = ACTION_KEYS.length; i < length; i++) {
            int key = ACTION_KEYS[i];

            checkDoNotIsActionPressedAfterReleaseDiferentKey(key);
        }
    }

    private void checkDoNotIsActionPressedAfterReleaseDiferentKey(
        final int key
    ) {
        input.pressKey(key);
        input.releaseKey(-key);
        Assert.assertFalse(input.isActionPressed());
    }

    public void testIsHoldingUp() {
        for (int i = 0, length = UP_KEYS.length; i < length; i++) {
            int key = UP_KEYS[i];

            checkIsHoldingUp(key);
        }
    }

    private void checkIsHoldingUp(final int key) {
        input.pressKey(key);

        Assert.assertTrue(input.isHoldingUp());

        input.update();

        Assert.assertTrue(input.isHoldingUp());

        input.releaseKey(key);

        Assert.assertFalse(input.isHoldingUp());
    }

    public void testIsHoldingRight() {
        for (int i = 0, length = RIGHT_KEYS.length; i < length; i++) {
            int key = RIGHT_KEYS[i];

            checkIsHoldingRight(key);
        }
    }

    private void checkIsHoldingRight(final int key) {
        input.pressKey(key);

        Assert.assertTrue(input.isHoldingRight());

        input.update();

        Assert.assertTrue(input.isHoldingRight());

        input.releaseKey(key);

        Assert.assertFalse(input.isHoldingRight());
    }

    public void testIsHoldingDown() {
        for (int i = 0, length = DOWN_KEYS.length; i < length; i++) {
            int key = DOWN_KEYS[i];

            checkIsHoldingDown(key);
        }
    }

    private void checkIsHoldingDown(final int key) {
        input.pressKey(key);

        Assert.assertTrue(input.isHoldingDown());

        input.update();

        Assert.assertTrue(input.isHoldingDown());

        input.releaseKey(key);

        Assert.assertFalse(input.isHoldingDown());
    }

    public void testIsHoldingLeft() {
        for (int i = 0, length = LEFT_KEYS.length; i < length; i++) {
            int key = LEFT_KEYS[i];

            checkIsHoldingLeft(key);
        }
    }

    private void checkIsHoldingLeft(final int key) {
        input.pressKey(key);

        Assert.assertTrue(input.isHoldingLeft());

        input.update();

        Assert.assertTrue(input.isHoldingLeft());

        input.releaseKey(key);

        Assert.assertFalse(input.isHoldingLeft());
    }
}

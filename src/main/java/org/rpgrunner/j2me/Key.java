package org.rpgrunner.j2me;

import javax.microedition.lcdui.game.GameCanvas;

public final class Key {
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

    private Key() { }

    public static boolean isAction(final int key) {
        return containsKey(ACTION_KEYS, key);
    }

    public static boolean isUp(final int key) {
        return containsKey(UP_KEYS, key);
    }

    public static boolean isRight(final int key) {
        return containsKey(RIGHT_KEYS, key);
    }

    public static boolean isDown(final int key) {
        return containsKey(DOWN_KEYS, key);
    }

    public static boolean isLeft(final int key) {
        return containsKey(LEFT_KEYS, key);
    }

    private static boolean containsKey(final int[] keys, final int key) {
        return (keys[0] == key) || (keys[1] == key);
    }
}

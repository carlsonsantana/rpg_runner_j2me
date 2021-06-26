package org.rpgrunner.test.helper;

import javax.microedition.lcdui.game.GameCanvas;

public final class KeyHelper {
    public static final int[] ACTION_KEYS = new int[] {
        GameCanvas.FIRE,
        GameCanvas.KEY_NUM5
    };
    public static final int[] UP_KEYS = new int[] {
        GameCanvas.UP,
        GameCanvas.KEY_NUM2
    };
    public static final int[] RIGHT_KEYS = new int[] {
        GameCanvas.RIGHT,
        GameCanvas.KEY_NUM6
    };
    public static final int[] DOWN_KEYS = new int[] {
        GameCanvas.DOWN,
        GameCanvas.KEY_NUM8
    };
    public static final int[] LEFT_KEYS = new int[] {
        GameCanvas.LEFT,
        GameCanvas.KEY_NUM4
    };

    private KeyHelper() { }
}

package org.rpgrunner.game.helper;

public class Camera {
    private final int screenWidth;
    private final int screenHeight;
    private int x;
    private int y;

    public Camera(final int newScreenWidth, final int newScreenHeight) {
        screenWidth = newScreenWidth;
        screenHeight = newScreenHeight;
        x = 0;
        y = 0;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

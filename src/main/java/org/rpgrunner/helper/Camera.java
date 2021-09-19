package org.rpgrunner.helper;

public class Camera {
    private final int screenWidth;
    private final int screenHeight;

    public Camera(final int newScreenWidth, final int newScreenHeight) {
        screenWidth = newScreenWidth;
        screenHeight = newScreenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}

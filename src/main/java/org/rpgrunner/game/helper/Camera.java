package org.rpgrunner.game.helper;

public class Camera {
    private final int screenWidth;

    public Camera(final int newScreenWidth) {
        screenWidth = newScreenWidth;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}

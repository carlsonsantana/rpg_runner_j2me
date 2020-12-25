package org.rpgrunner.helper;

import org.rpgrunner.map.Map;
import org.rpgrunner.character.CharacterAnimation;

public class Camera {
    private static final int TILE_WIDTH = 16;
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

    public void centerCamera(
        final Map map,
        final CharacterAnimation characterAnimation
    ) {
        x = getCenterCamera(
            characterAnimation.getScreenX() + (TILE_WIDTH / 2),
            screenWidth,
            map.getWidth()
        );
        y = getCenterCamera(
            characterAnimation.getScreenY(),
            screenHeight,
            map.getHeight()
        );
    }

    private int getCenterCamera(
        final int characterScreenPosition,
        final int screenSize,
        final int mapSize
    ) {
        int screenMiddle = screenSize / 2;
        int characterPosition = characterScreenPosition;
        int max = (mapSize * TILE_WIDTH) - screenSize;
        int cameraPositionWithoutCorrection = (
            characterPosition - screenMiddle
        );

        return Math.min(Math.max(cameraPositionWithoutCorrection, 0), max);
    }
}

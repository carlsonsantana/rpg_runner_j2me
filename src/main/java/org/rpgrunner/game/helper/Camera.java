package org.rpgrunner.game.helper;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterAnimation;
import org.rpgrunner.game.character.CharacterElement;

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
        final CharacterElement characterElement
    ) {
        GameCharacter character = characterElement.getCharacter();
        CharacterAnimation characterAnimation = (
            characterElement.getCharacterAnimation()
        );

        x = getCenterCamera(
            character.getMapPositionX(),
            characterAnimation.getScreenX(),
            screenWidth,
            map.getWidth()
        );
        y = getCenterCamera(
            character.getMapPositionY(),
            characterAnimation.getScreenY(),
            screenHeight,
            map.getHeight()
        );
    }

    public int getCenterCamera(
        final int characterMapPosition,
        final int characterScreenPosition,
        final int screenSize,
        final int mapSize
    ) {
        int screenMiddle = screenSize / 2;
        int characterPosition = (
            (characterMapPosition * TILE_WIDTH)
            + (characterScreenPosition % TILE_WIDTH)
        );
        int max = (mapSize * TILE_WIDTH) - screenMiddle;
        int cameraPositionWithoutCorrection = (
            characterPosition - screenMiddle
        );

        return Math.min(Math.max(cameraPositionWithoutCorrection, 0), max);
    }
}

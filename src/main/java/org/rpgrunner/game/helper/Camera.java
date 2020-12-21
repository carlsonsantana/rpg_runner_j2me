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

        int screenMiddleHeight = screenHeight / 2;
        int characterPositionY = (
            (character.getMapPositionY() * TILE_WIDTH)
            + (characterAnimation.getScreenY() % TILE_WIDTH)
        );
        int maxY = (map.getHeight() * TILE_WIDTH) - screenMiddleHeight;
        int cameraPositionYWithoutCorrection = (
            characterPositionY - screenMiddleHeight
        );

        y = Math.min(Math.max(cameraPositionYWithoutCorrection, 0), maxY);
    }
}

package org.rpgrunner.game.character;

public interface CharacterAnimation {
    int getScreenX();
    int getScreenY();
    int getWidth();
    int getHeight();
    void setScreenPosition(int screenX, int screenY);
    void startAnimation();
    boolean isAnimationComplete();
    Object getSprite();
    GameCharacter getCharacter();
}

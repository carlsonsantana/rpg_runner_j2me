package org.rpgrunner.game.character;

public interface CharacterAnimation {
    int getScreenX();
    int getScreenY();
    int getWidth();
    int getHeight();
    void setScreenPosition(int screenX, int screenY);
    void startAnimation();
    void doAnimation();
    boolean isAnimationComplete();
    Object getSprite();
    void setCharacterElement(CharacterElement characterElement);
}

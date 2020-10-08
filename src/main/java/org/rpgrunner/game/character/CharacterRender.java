package org.rpgrunner.game.character;

public interface CharacterRender {
    void preRender();
    void render();
    boolean isAnimationComplete();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void setPosition(int x, int y);
}

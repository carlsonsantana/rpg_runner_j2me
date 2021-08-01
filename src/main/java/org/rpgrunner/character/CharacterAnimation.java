package org.rpgrunner.character;

public interface CharacterAnimation {
    int getScreenX();
    int getScreenY();
    void updateScreenPositionFromMapPosition();
    void startAnimation();
    void doAnimation();
    Object getSprite();
}

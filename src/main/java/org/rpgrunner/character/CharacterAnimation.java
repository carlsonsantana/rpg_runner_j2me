package org.rpgrunner.character;

public interface CharacterAnimation {
    int getScreenX();
    int getScreenY();
    void startAnimation();
    void doAnimation();
    Object getSprite();
    void setCharacterElement(CharacterElement characterElement);
}

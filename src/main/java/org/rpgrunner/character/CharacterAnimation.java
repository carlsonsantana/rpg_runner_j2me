package org.rpgrunner.character;

import org.rpgrunner.event.action.Action;

public interface CharacterAnimation {
    int getScreenX();
    int getScreenY();
    void updateScreenPositionFromMapPosition();
    void startAnimation();
    void doAnimation();
    Object getSprite();
    void moveUp();
    void moveRight();
    void moveDown();
    void moveLeft();
    byte getDirection();
    boolean isMoving();
    void finishMove();
    void cancelMove();
    void setMapPosition(int newMapPositionX, int newMapPositionY);
    int getMapPositionX();
    int getMapPositionY();
    int getMapNextPositionX();
    int getMapNextPositionY();
    Action getInteractiveAction(byte interactDirection);
}

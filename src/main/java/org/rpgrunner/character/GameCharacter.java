package org.rpgrunner.character;

import org.rpgrunner.Direction;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.action.Action;

public abstract class GameCharacter {
    private final byte idSprite;
    private final CharacterEventListener characterEventListener;
    private byte direction;
    private int mapPositionX;
    private int mapPositionY;
    private int mapNextPositionX;
    private int mapNextPositionY;

    public GameCharacter(
        final byte characterIDSprite,
        final CharacterEventListener newCharacterEventListener
    ) {
        idSprite = characterIDSprite;
        direction = Direction.DOWN;
        characterEventListener = newCharacterEventListener;
    }

    public byte getIDSprite() {
        return idSprite;
    }

    public void moveUp() {
        if (isMoving()) {
            return;
        }

        direction = Direction.UP;
        mapNextPositionY = mapPositionY - 1;
    }

    public void moveRight() {
        if (isMoving()) {
            return;
        }

        direction = Direction.RIGHT;
        mapNextPositionX = mapPositionX + 1;
    }

    public void moveDown() {
        if (isMoving()) {
            return;
        }

        direction = Direction.DOWN;
        mapNextPositionY = mapPositionY + 1;
    }

    public void moveLeft() {
        if (isMoving()) {
            return;
        }

        direction = Direction.LEFT;
        mapNextPositionX = mapPositionX - 1;
    }

    public byte getDirection() {
        return direction;
    }

    public boolean isMoving() {
        return (
            mapPositionX != mapNextPositionX
            || mapPositionY != mapNextPositionY
        );
    }

    public void finishMove() {
        mapPositionX = mapNextPositionX;
        mapPositionY = mapNextPositionY;
    }

    public void cancelMove() {
        mapNextPositionX = mapPositionX;
        mapNextPositionY = mapPositionY;
    }

    public void setMapPosition(
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        mapPositionX = newMapPositionX;
        mapNextPositionX = newMapPositionX;
        mapPositionY = newMapPositionY;
        mapNextPositionY = newMapPositionY;
    }

    public int getMapPositionX() {
        return mapPositionX;
    }

    public int getMapPositionY() {
        return mapPositionY;
    }

    public int getMapNextPositionX() {
        return mapNextPositionX;
    }

    public int getMapNextPositionY() {
        return mapNextPositionY;
    }

    public Action getInteractiveAction(final byte interactDirection) {
        return characterEventListener.interact(interactDirection);
    }

    public CharacterEventListener getCharacterEventListener() {
        return characterEventListener;
    }

    public abstract int getScreenX();
    public abstract int getScreenY();
    public abstract void updateScreenPositionFromMapPosition();
    public abstract void startAnimation();
    public abstract void doAnimation();
    public abstract Object getSprite();
}

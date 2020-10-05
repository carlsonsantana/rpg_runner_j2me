package org.rpgrunner.game.character;

import org.rpgrunner.game.Direction;

public class GameCharacter {
    private final String fileBaseName;
    private byte direction;
    private int mapPositionX;
    private int mapPositionY;
    private int mapNextPositionX;
    private int mapNextPositionY;

    public GameCharacter(final String characterFileBaseName) {
        fileBaseName = characterFileBaseName;
        setDirection(Direction.DOWN);
    }

    public String getFileBaseName() {
        return fileBaseName;
    }

    public void moveUp() {
        if (isMoving()) {
            return;
        }
        setDirection(Direction.UP);
        mapNextPositionY = mapPositionY - 1;
    }

    public void moveRight() {
        if (isMoving()) {
            return;
        }
        setDirection(Direction.RIGHT);
        mapNextPositionX = mapPositionX + 1;
    }

    public void moveDown() {
        if (isMoving()) {
            return;
        }
        setDirection(Direction.DOWN);
        mapNextPositionY = mapPositionY + 1;
    }

    public void moveLeft() {
        if (isMoving()) {
            return;
        }
        setDirection(Direction.LEFT);
        mapNextPositionX = mapPositionX - 1;
    }

    public void setDirection(final byte newDirection) {
        direction = newDirection;
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
}

package org.rpgrunner.game.character;

import org.rpgrunner.game.Direction;

public class GameCharacter {
    private final String fileBaseName;
    private byte direction;
    private boolean moving;

    public GameCharacter(final String characterFileBaseName) {
        fileBaseName = characterFileBaseName;
        setDirection(Direction.DOWN);
        moving = false;
    }

    public String getFileBaseName() {
        return fileBaseName;
    }

    public void moveUp() {
        setDirection(Direction.UP);
        moving = true;
    }

    public void moveRight() {
        setDirection(Direction.RIGHT);
        moving = true;
    }

    public void moveDown() {
        setDirection(Direction.DOWN);
        moving = true;
    }

    public void moveLeft() {
        setDirection(Direction.LEFT);
        moving = true;
    }

    public void stop() {
        moving = false;
    }

    public void setDirection(final byte newDirection) {
        direction = newDirection;
    }

    public byte getDirection() {
        return direction;
    }

    public boolean isMoving() {
        return moving;
    }
}

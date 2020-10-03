package org.rpgrunner.game.character;

import org.rpgrunner.game.Direction;

public class GameCharacter {
    private final String fileBaseName;
    private byte direction;

    public GameCharacter(final String characterFileBaseName) {
        fileBaseName = characterFileBaseName;
        setDirection(Direction.DOWN);
    }

    public String getFileBaseName() {
        return fileBaseName;
    }

    public void moveUp() {
        setDirection(Direction.UP);
    }

    public void moveRight() {
        setDirection(Direction.RIGHT);
    }

    public void moveDown() {
        setDirection(Direction.DOWN);
    }

    public void moveLeft() {
        setDirection(Direction.LEFT);
    }

    public void setDirection(final byte newDirection) {
        direction = newDirection;
    }

    public byte getDirection() {
        return direction;
    }
}

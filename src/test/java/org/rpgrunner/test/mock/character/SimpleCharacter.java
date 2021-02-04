package org.rpgrunner.test.mock.character;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;

public class SimpleCharacter extends GameCharacter {
    private byte direction;
    public SimpleCharacter() {
        super(null);
    }

    public void moveUp() {
        direction = Direction.UP;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
    }

    public void moveDown() {
        direction = Direction.DOWN;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
    }

    public byte getDirection() {
        return direction;
    }
}

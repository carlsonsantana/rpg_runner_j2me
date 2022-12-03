package org.rpgrunner.test.mock.character;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;

public class SimpleCharacter extends GameCharacter {
    private byte direction;
    private boolean moving;

    public SimpleCharacter() {
        super(null);
    }

    public void moveUp() {
        direction = Direction.UP;
        moving = true;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
        moving = true;
    }

    public void moveDown() {
        direction = Direction.DOWN;
        moving = true;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
        moving = true;
    }

    public byte getDirection() {
        return direction;
    }

    public void setMoving(final boolean newMoving) {
        moving = newMoving;
    }

    public boolean isMoving() {
        return moving;
    }

    public void cancelMove() {
        moving = false;
    }

    public int getScreenX() {
        return 0;
    }

    public int getScreenY() {
        return 0;
    }

    public void updateScreenPositionFromMapPosition() { }

    public void startAnimation() { }

    public void doAnimation() { }

    public Object getSprite() {
        return null;
    }
}

package org.rpgrunner.test.mock.character;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.action.CharacterActionSpy;

public class CharacterSpy extends GameCharacter {
    private static final byte INITIAL_DIRECTION = 0;
    private int initialPositionX;
    private int initialPositionY;
    private int additionalNextPositionX;
    private int additionalNextPositionY;
    private byte direction;

    public CharacterSpy() {
        super(null);

        direction = INITIAL_DIRECTION;
    }

    public void setInitialPosition(
        final int newInitialPositionX,
        final int newInitialPositionY
    ) {
        initialPositionX = newInitialPositionX;
        initialPositionY = newInitialPositionY;
    }

    public void setAdditionalNextPosition(
        final int newAdditionalNextPositionX,
        final int newAdditionalNextPositionY
    ) {
        additionalNextPositionX = newAdditionalNextPositionX;
        additionalNextPositionY = newAdditionalNextPositionY;
    }

    public int getMapPositionX() {
        return super.getMapPositionX() + initialPositionX;
    }

    public int getMapPositionY() {
        return super.getMapPositionY() + initialPositionY;
    }

    public int getMapNextPositionX() {
        return (
            super.getMapNextPositionX()
            + initialPositionX
            + additionalNextPositionX
        );
    }

    public int getMapNextPositionY() {
        return (
            super.getMapNextPositionY()
            + initialPositionY
            + additionalNextPositionY
        );
    }

    public Action getInteractiveAction(final byte interactDirection) {
        return new CharacterActionSpy(this);
    }

    public void setDirection(final byte newDirection) {
        direction = newDirection;
    }

    public byte getDirection() {
        if (direction == INITIAL_DIRECTION) {
            return super.getDirection();
        }

        return direction;
    }

    public void moveTo(final byte newDirection) {
        if (Direction.isUp(newDirection)) {
            moveUp();
        } else if (Direction.isRight(newDirection)) {
            moveRight();
        } else if (Direction.isDown(newDirection)) {
            moveDown();
        } else if (Direction.isLeft(newDirection)) {
            moveLeft();
        }
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

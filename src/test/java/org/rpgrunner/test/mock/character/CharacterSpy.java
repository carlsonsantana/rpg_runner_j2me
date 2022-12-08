package org.rpgrunner.test.mock.character;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.action.CharacterActionSpy;

public class CharacterSpy extends GameCharacter {
    private static final byte INITIAL_DIRECTION = 0;
    private int initialPositionX;
    private int initialPositionY;
    private int additionalNextPositionX;
    private int additionalNextPositionY;
    private byte direction;
    private CharacterEventListener characterEventListener;
    private boolean startAnimationCalled;
    private int screenX;
    private int screenY;
    private boolean updateScreenPositionFromMapPositionCalled;
    private boolean doAnimationCalled;

    public CharacterSpy() {
        super(null);

        direction = INITIAL_DIRECTION;
        startAnimationCalled = false;
        screenX = 0;
        screenY = 0;
        updateScreenPositionFromMapPositionCalled = false;
        doAnimationCalled = false;
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
        if (characterEventListener == null) {
            return new CharacterActionSpy(this);
        }

        return characterEventListener.interact(interactDirection);
    }

    public void setCharacterEventListener(
        final CharacterEventListener newCharacterEventListener
    ) {
        characterEventListener = newCharacterEventListener;
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
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenPosition(final int newScreenX, final int newScreenY) {
        screenX = newScreenX;
        screenY = newScreenY;
    }

    public void updateScreenPositionFromMapPosition() {
        updateScreenPositionFromMapPositionCalled = true;
    }

    public boolean isUpdateScreenPositionFromMapPositionCalled() {
        return updateScreenPositionFromMapPositionCalled;
    }

    public void startAnimation() {
        startAnimationCalled = true;
    }

    public boolean isStartAnimationCalled() {
        return startAnimationCalled;
    }

    public void doAnimation() {
        doAnimationCalled = true;
    }

    public boolean isDoAnimationCalled() {
        return doAnimationCalled;
    }

    public Object getSprite() {
        return null;
    }
}

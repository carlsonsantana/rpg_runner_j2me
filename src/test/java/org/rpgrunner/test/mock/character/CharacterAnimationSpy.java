package org.rpgrunner.test.mock.character;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.action.CharacterActionSpy;

public class CharacterAnimationSpy extends GameCharacter {
    private boolean startAnimationCalled;
    private int screenX;
    private int screenY;
    private boolean updateScreenPositionFromMapPositionCalled;
    private boolean doAnimationCalled;
    private CharacterEventListener characterEventListener;
    private int mapPositionX;
    private int mapPositionY;
    private int nextMapPositionX;
    private int nextMapPositionY;
    private byte direction;
    private boolean moving;

    public CharacterAnimationSpy() {
        super(null);
        startAnimationCalled = false;
        screenX = 0;
        screenY = 0;
        updateScreenPositionFromMapPositionCalled = false;
        doAnimationCalled = false;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void updateScreenPositionFromMapPosition() {
        updateScreenPositionFromMapPositionCalled = true;
    }

    public boolean isUpdateScreenPositionFromMapPositionCalled() {
        return updateScreenPositionFromMapPositionCalled;
    }

    public void setScreenPosition(final int newScreenX, final int newScreenY) {
        screenX = newScreenX;
        screenY = newScreenY;
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

    public boolean isAnimationComplete() {
        return false;
    }

    public Object getSprite() {
        return null;
    }

    public void moveUp() {
        direction = Direction.UP;
        moving = true;
        nextMapPositionY--;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
        moving = true;
        nextMapPositionX++;
    }

    public void moveDown() {
        direction = Direction.DOWN;
        moving = true;
        nextMapPositionY++;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
        moving = true;
        nextMapPositionX--;
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

    public void finishMove() { }

    public void cancelMove() {
        moving = false;
    }

    public void setMapPosition(
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        mapPositionX = newMapPositionX;
        mapPositionY = newMapPositionY;
        nextMapPositionX = newMapPositionX;
        nextMapPositionY = newMapPositionY;
    }

    public int getMapPositionX() {
        return mapPositionX;
    }

    public int getMapPositionY() {
        return mapPositionY;
    }

    public int getMapNextPositionX() {
        return nextMapPositionX;
    }

    public int getMapNextPositionY() {
        return nextMapPositionY;
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
}

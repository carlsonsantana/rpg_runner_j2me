package org.rpgrunner.test.mock.character;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.action.Action;

public class CharacterAnimationSpy implements CharacterAnimation {
    private boolean startAnimationCalled;
    private int screenX;
    private int screenY;
    private boolean updateScreenPositionFromMapPositionCalled;
    private boolean doAnimationCalled;
    private byte idSprite;
    private CharacterEventListener characterEventListener;
    private int mapPositionX;
    private int mapPositionY;
    private byte direction;
    private boolean moving;

    public CharacterAnimationSpy() {
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
    }

    public int getMapPositionX() {
        return mapPositionX;
    }

    public int getMapPositionY() {
        return mapPositionY;
    }

    public int getMapNextPositionX() {
        return 0;
    }

    public int getMapNextPositionY() {
        return 0;
    }

    public Action getInteractiveAction(final byte interactDirection) {
        return characterEventListener.interact(interactDirection);
    }

    public byte getIDSprite() {
        return idSprite;
    }

    public void setIDSprite(final byte newIDSprite) {
        idSprite = newIDSprite;
    }

    public CharacterEventListener getCharacterEventListener() {
        return null;
    }

    public void setCharacterEventListener(
        final CharacterEventListener newCharacterEventListener
    ) {
        characterEventListener = newCharacterEventListener;
    }
}

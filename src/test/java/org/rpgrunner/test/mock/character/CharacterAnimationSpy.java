package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.action.Action;

public class CharacterAnimationSpy implements CharacterAnimation {
    private boolean startAnimationCalled;
    private int screenX;
    private int screenY;
    private boolean updateScreenPositionFromMapPositionCalled;
    private boolean doAnimationCalled;

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

    public void moveUp() { }

    public void moveRight() { }

    public void moveDown() { }

    public void moveLeft() { }

    public byte getDirection() {
        return 0;
    }

    public boolean isMoving() {
        return false;
    }

    public void finishMove() { }

    public void cancelMove() { }

    public void setMapPosition(
        final int newMapPositionX,
        final int newMapPositionY
    ) { }

    public int getMapPositionX() {
        return 0;
    }

    public int getMapPositionY() {
        return 0;
    }

    public int getMapNextPositionX() {
        return 0;
    }

    public int getMapNextPositionY() {
        return 0;
    }

    public Action getInteractiveAction(final byte interactDirection) {
        return null;
    }

    public byte getIDSprite() {
        return 0;
    }

    public CharacterEventListener getCharacterEventListener() {
        return null;
    }
}

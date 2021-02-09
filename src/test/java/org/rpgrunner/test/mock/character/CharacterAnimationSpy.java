package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;

public class CharacterAnimationSpy implements CharacterAnimation {
    private boolean startAnimationCalled;
    private int screenX;
    private int screenY;
    private boolean updateScreenPositionFromMapPositionCalled;

    public CharacterAnimationSpy() {
        startAnimationCalled = false;
        screenX = 0;
        screenY = 0;
        updateScreenPositionFromMapPositionCalled = false;
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

    public void doAnimation() { }

    public boolean isStartAnimationCalled() {
        return startAnimationCalled;
    }

    public boolean isAnimationComplete() {
        return false;
    }

    public Object getSprite() {
        return null;
    }

    public void setCharacterElement(final CharacterElement characterElement) { }
}
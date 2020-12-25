package org.rpgrunner.test.mock;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;

public class CharacterAnimationSpy implements CharacterAnimation {
    private boolean startAnimationCalled;
    private int screenX;
    private int screenY;

    public CharacterAnimationSpy() {
        startAnimationCalled = false;
        screenX = 0;
        screenY = 0;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
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

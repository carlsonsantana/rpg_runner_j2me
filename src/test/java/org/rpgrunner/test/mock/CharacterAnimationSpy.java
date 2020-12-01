package org.rpgrunner.test.mock;

import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterAnimation;

public class CharacterAnimationSpy implements CharacterAnimation {
    private boolean startAnimationCalled;

    public CharacterAnimationSpy() {
        startAnimationCalled = false;
    }

    public int getScreenX() {
        return 0;
    }

    public int getScreenY() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public void setScreenPosition(int screenX, int screenY) { }

    public void startAnimation() {
        startAnimationCalled = true;
    }

    public boolean isStartAnimationCalled() {
        return startAnimationCalled;
    }

    public boolean isAnimationComplete() {
        return false;
    }

    public Object getSprite() {
        return null;
    }

    public GameCharacter getCharacter() {
        return null;
    }
}

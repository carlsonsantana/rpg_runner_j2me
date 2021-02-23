package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.action.CharacterActionSpy;

public class CharacterSpy extends GameCharacter {
    private int initialPositionX;
    private int initialPositionY;

    public CharacterSpy(final String fileBaseName) {
        super(fileBaseName, null);
    }

    public void setInitialPosition(
        final int newInitialPositionX,
        final int newInitialPositionY
    ) {
        initialPositionX = newInitialPositionX;
        initialPositionY = newInitialPositionY;
    }

    public int getMapPositionX() {
        return super.getMapPositionX() + initialPositionX;
    }

    public int getMapPositionY() {
        return super.getMapPositionY() + initialPositionY;
    }

    public int getMapNextPositionX() {
        return super.getMapNextPositionX() + initialPositionX;
    }

    public int getMapNextPositionY() {
        return super.getMapNextPositionY() + initialPositionY;
    }

    public Action getInteractiveAction() {
        return new CharacterActionSpy(this);
    }
}

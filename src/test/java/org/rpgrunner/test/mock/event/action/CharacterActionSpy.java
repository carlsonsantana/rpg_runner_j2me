package org.rpgrunner.test.mock.event.action;

import org.rpgrunner.character.GameCharacter;

public class CharacterActionSpy extends ActionSpy {
    private final GameCharacter character;

    public CharacterActionSpy(final GameCharacter characterOfAction) {
        character = characterOfAction;
    }

    public GameCharacter getCharacter() {
        return character;
    }
}

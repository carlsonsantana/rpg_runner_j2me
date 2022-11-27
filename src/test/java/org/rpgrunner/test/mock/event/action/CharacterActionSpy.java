package org.rpgrunner.test.mock.event.action;

import org.rpgrunner.character.CharacterAnimation;

public class CharacterActionSpy extends ActionSpy {
    private final CharacterAnimation character;

    public CharacterActionSpy(final CharacterAnimation characterOfAction) {
        character = characterOfAction;
    }

    public CharacterAnimation getCharacter() {
        return character;
    }
}

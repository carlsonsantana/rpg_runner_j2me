package org.rpgrunner.character;

public class CharacterElement {
    private final GameCharacter character;

    public CharacterElement(final GameCharacter newCharacter) {
        character = newCharacter;
    }

    public GameCharacter getCharacterAnimation() {
        return character;
    }
}

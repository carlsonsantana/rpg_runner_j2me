package org.rpgrunner.game.character;

public class CharacterElement {
    private GameCharacter character;

    public CharacterElement(final GameCharacter newCharacter) {
        character = newCharacter;
    }

    public GameCharacter getCharacter() {
        return character;
    }
}

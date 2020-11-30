package org.rpgrunner.game.character;

public class CharacterElement {
    private GameCharacter character;
    private CharacterAnimation characterAnimation;

    public CharacterElement(
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation
    ) {
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }
}

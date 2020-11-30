package org.rpgrunner.game.character;

public class CharacterElement {
    private final CharacterMovimentEvent characterMovimentEvent;
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;

    public CharacterElement(
        final CharacterMovimentEvent newCharacterMovimentEvent,
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation
    ) {
        characterMovimentEvent = newCharacterMovimentEvent;
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
    }

    public CharacterMovimentEvent getCharacterMovimentEvent() {
        return characterMovimentEvent;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }
}

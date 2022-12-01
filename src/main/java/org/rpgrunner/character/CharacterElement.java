package org.rpgrunner.character;

import org.rpgrunner.character.movement.MovementCommand;

public class CharacterElement {
    private final CharacterAnimation character;
    private final CharacterAnimation characterAnimation;
    private final MovementCommand movementCommand;

    public CharacterElement(
        final CharacterAnimation newCharacter,
        final CharacterAnimation newCharacterAnimation,
        final MovementCommand characterCommand
    ) {
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
        movementCommand = characterCommand;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }

    public MovementCommand getMovementCommand() {
        return movementCommand;
    }
}

package org.rpgrunner.character;

import org.rpgrunner.character.movement.MovementCommand;

public class CharacterElement {
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;
    private final MovementCommand movementCommand;

    public CharacterElement(
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation,
        final MovementCommand characterCommand
    ) {
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
        movementCommand = characterCommand;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }

    public MovementCommand getMovementCommand() {
        return movementCommand;
    }
}

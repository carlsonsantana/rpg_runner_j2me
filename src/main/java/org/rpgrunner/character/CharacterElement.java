package org.rpgrunner.character;

import org.rpgrunner.character.movement.MovementCommand;

public class CharacterElement {
    private final CharacterAnimation character;
    private final MovementCommand movementCommand;

    public CharacterElement(
        final CharacterAnimation newCharacter,
        final MovementCommand characterCommand
    ) {
        character = newCharacter;
        movementCommand = characterCommand;
    }

    public CharacterAnimation getCharacterAnimation() {
        return character;
    }

    public MovementCommand getMovementCommand() {
        return movementCommand;
    }
}

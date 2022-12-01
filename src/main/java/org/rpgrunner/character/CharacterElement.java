package org.rpgrunner.character;

import org.rpgrunner.character.movement.MovementCommand;

public class CharacterElement {
    private final GameCharacter character;
    private final MovementCommand movementCommand;

    public CharacterElement(
        final GameCharacter newCharacter,
        final MovementCommand characterCommand
    ) {
        character = newCharacter;
        movementCommand = characterCommand;
    }

    public GameCharacter getCharacterAnimation() {
        return character;
    }

    public MovementCommand getMovementCommand() {
        return movementCommand;
    }
}

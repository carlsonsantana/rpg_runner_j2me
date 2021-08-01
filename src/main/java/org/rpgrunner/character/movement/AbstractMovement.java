package org.rpgrunner.character.movement;

import org.rpgrunner.character.GameCharacter;

public abstract class AbstractMovement implements MovementCommand {
    private final GameCharacter character;

    public AbstractMovement(final GameCharacter controlledCharacter) {
        character = controlledCharacter;
    }

    public void execute() {
        if (!character.isMoving()) {
            executeMovement();
        }
    }

    protected abstract void executeMovement();
}

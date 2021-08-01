package org.rpgrunner.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.MapHelper;

public abstract class AbstractMovement implements MovementCommand {
    private final GameCharacter character;
    private final MapHelper mapHelper;

    public AbstractMovement(
        final GameCharacter controlledCharacter,
        final MapHelper newMapHelper
    ) {
        character = controlledCharacter;
        mapHelper = newMapHelper;
    }

    public void execute() {
        if (character.isMoving()) {
            return;
        }

        executeMovement();

        if (!mapHelper.canMove(character)) {
            character.cancelMove();
        }
    }

    protected abstract void executeMovement();
}

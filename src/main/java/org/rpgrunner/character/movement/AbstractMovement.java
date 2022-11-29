package org.rpgrunner.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.helper.MapHelper;

public abstract class AbstractMovement implements MovementCommand {
    private final CharacterAnimation character;
    private final MapHelper mapHelper;

    public AbstractMovement(
        final CharacterAnimation controlledCharacter,
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

        character.startAnimation();
    }

    protected abstract void executeMovement();
}

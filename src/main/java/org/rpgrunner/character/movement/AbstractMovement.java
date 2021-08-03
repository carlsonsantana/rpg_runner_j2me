package org.rpgrunner.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.MapHelper;

public abstract class AbstractMovement implements MovementCommand {
    private final GameCharacter character;
    private final MapHelper mapHelper;
    private final CharacterAnimation characterAnimation;

    public AbstractMovement(
        final GameCharacter controlledCharacter,
        final CharacterAnimation newCharacterAnimation,
        final MapHelper newMapHelper
    ) {
        character = controlledCharacter;
        characterAnimation = newCharacterAnimation;
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

        characterAnimation.doAnimation();
    }

    protected abstract void executeMovement();
}

package org.rpgrunner.character;

import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.helper.MapHelper;

public class CharacterElement {
    private final MapHelper mapHelper;
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;
    private final MovementCommand movementCommand;

    public CharacterElement(
        final MapHelper newMapHelper,
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation,
        final MovementCommand characterCommand
    ) {
        mapHelper = newMapHelper;
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

    public void onMove() {
        if (!mapHelper.canMove(character)) {
            character.cancelMove();
        }

        characterAnimation.startAnimation();
    }

    public void onAnimationComplete() {
        character.finishMove();
    }
}

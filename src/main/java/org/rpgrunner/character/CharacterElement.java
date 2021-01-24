package org.rpgrunner.character;

import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.character.movement.MovementCommand;

public class CharacterElement {
    private final CollisionDetector collisionDetector;
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;
    private final MovementCommand movementCommand;

    public CharacterElement(
        final CollisionDetector newCollisionDetector,
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation,
        final MovementCommand characterCommand
    ) {
        collisionDetector = newCollisionDetector;
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
        if (!collisionDetector.canMove(character)) {
            character.cancelMove();
        }

        characterAnimation.startAnimation();
    }

    public void onAnimationComplete() {
        character.finishMove();
    }
}

package org.rpgrunner.character;

import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.character.command.MovimentCommand;

public class CharacterElement {
    private final CollisionDetector collisionDetector;
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;
    private final MovimentCommand movimentCommand;

    public CharacterElement(
        final CollisionDetector newCollisionDetector,
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation,
        final MovimentCommand characterCommand
    ) {
        collisionDetector = newCollisionDetector;
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
        movimentCommand = characterCommand;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }

    public MovimentCommand getMovimentCommand() {
        return movimentCommand;
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

package org.rpgrunner.character;

import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.character.command.Command;

public class CharacterElement {
    private final CollisionDetector collisionDetector;
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;
    private final Command command;

    public CharacterElement(
        final CollisionDetector newCollisionDetector,
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation,
        final Command characterCommand
    ) {
        collisionDetector = newCollisionDetector;
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
        command = characterCommand;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }

    public Command getCommand() {
        return command;
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

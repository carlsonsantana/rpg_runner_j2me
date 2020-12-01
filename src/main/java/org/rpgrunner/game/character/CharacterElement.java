package org.rpgrunner.game.character;

import org.rpgrunner.game.helper.CollisionDetector;

public class CharacterElement {
    private final CollisionDetector collisionDetector;
    private final GameCharacter character;
    private final CharacterAnimation characterAnimation;

    public CharacterElement(
        final CollisionDetector newCollisionDetector,
        final GameCharacter newCharacter,
        final CharacterAnimation newCharacterAnimation
    ) {
        collisionDetector = newCollisionDetector;
        character = newCharacter;
        characterAnimation = newCharacterAnimation;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public CharacterAnimation getCharacterAnimation() {
        return characterAnimation;
    }

    public void onMove() {
        if (!collisionDetector.canMove(character)) {
            character.cancelMove();
        }
        characterAnimation.startAnimation();
    }
}

package org.rpgrunner.game.character;

import org.rpgrunner.game.helper.CollisionDetector;

public class CharacterMovimentEvent {
    private CollisionDetector collisionDetector;

    public CharacterMovimentEvent(
        final CollisionDetector newCollisionDetector
    ) {
        collisionDetector = newCollisionDetector;
    }

    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public boolean onMove(final CharacterElement characterElement) {
        GameCharacter character = characterElement.getCharacter();
        return collisionDetector.canMove(character);
    }
}
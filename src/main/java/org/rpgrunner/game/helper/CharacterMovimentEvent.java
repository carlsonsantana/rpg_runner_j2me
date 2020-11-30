package org.rpgrunner.game.helper;

import org.rpgrunner.game.character.GameCharacter;

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

    public boolean onMove(final GameCharacter character) {
        return collisionDetector.canMove(character);
    }
}

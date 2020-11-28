package org.rpgrunner.game.helper;

import org.rpgrunner.game.character.GameCharacter;

public class CharacterMovimentEventFacade {
    private static CollisionDetector collisionDetector;

    private CharacterMovimentEventFacade() { }

    public static void setCollisionDetector(
        final CollisionDetector newCollisionDetector
    ) {
        collisionDetector = newCollisionDetector;
    }

    public static CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public static boolean onMove(final GameCharacter character) {
        return collisionDetector.canMove(character);
    }
}

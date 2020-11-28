package org.rpgrunner.game.helper;

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
}

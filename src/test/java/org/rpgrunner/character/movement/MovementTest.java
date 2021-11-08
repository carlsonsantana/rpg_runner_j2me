package org.rpgrunner.character.movement;

public interface MovementTest {
    void testDoNotMoveWhenCharacterIsMoving();
    void testCancelMoveWhenCharacterCantMove();
}

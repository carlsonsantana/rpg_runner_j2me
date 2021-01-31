package org.rpgrunner.test.helper;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;

public abstract class TestAllDirections {
    public void test() {
        testNewCharacterMoveToUp();
        testNewCharacterMoveToRight();
        testNewCharacterMoveToDown();
        testNewCharacterMoveToLeft();
    }

    private void testNewCharacterMoveToUp() {
        testMoveToUp(RandomGenerator.generateRandomCharacter());
    }

    private void testNewCharacterMoveToRight() {
        testMoveToRight(RandomGenerator.generateRandomCharacter());
    }

    private void testNewCharacterMoveToDown() {
        testMoveToDown(RandomGenerator.generateRandomCharacter());
    }

    private void testNewCharacterMoveToLeft() {
        testMoveToLeft(RandomGenerator.generateRandomCharacter());
    }

    public void testMoveToUp(final GameCharacter character) {
        character.moveUp();

        testMoveToDirection(character, Direction.UP, 0, -1);
    }

    public void testMoveToRight(final GameCharacter character) {
        character.moveRight();

        testMoveToDirection(character, Direction.RIGHT, 1, 0);
    }

    public void testMoveToDown(final GameCharacter character) {
        character.moveDown();

        testMoveToDirection(character, Direction.DOWN, 0, 1);
    }

    public void testMoveToLeft(final GameCharacter character) {
        character.moveLeft();

        testMoveToDirection(character, Direction.LEFT, -1, 0);
    }

    private void testMoveToDirection(
        final GameCharacter character,
        final byte direction,
        final int increaseX,
        final int increaseY
    ) {
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX + increaseX;
        int newPositionY = initialPositionY + increaseY;

        testMovement(
            character,
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public abstract void testMovement(
        final GameCharacter character,
        final byte direction,
        final int initialPositionX,
        final int initialPositionY,
        final int newPositionX,
        final int newPositionY
    );
}

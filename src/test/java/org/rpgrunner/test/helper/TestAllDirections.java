package org.rpgrunner.test.helper;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;

public abstract class TestAllDirections {
    private GameCharacter character;

    public TestAllDirections(final GameCharacter characterTest) {
        character = characterTest;
    }

    public void test() {
        testNewCharacterMoveToUp();
        testNewCharacterMoveToRight();
        testNewCharacterMoveToDown();
        testNewCharacterMoveToLeft();
    }

    private void testNewCharacterMoveToUp() {
        character = RandomGenerator.generateRandomCharacter();
        testMoveToUp();
    }

    private void testNewCharacterMoveToRight() {
        character = RandomGenerator.generateRandomCharacter();
        testMoveToRight();
    }

    private void testNewCharacterMoveToDown() {
        character = RandomGenerator.generateRandomCharacter();
        testMoveToDown();
    }

    private void testNewCharacterMoveToLeft() {
        character = RandomGenerator.generateRandomCharacter();
        testMoveToLeft();
    }

    public void testMoveToUp() {
        character.moveUp();

        testMoveToDirection(Direction.UP, 0, -1);
    }

    public void testMoveToRight() {
        character.moveRight();

        testMoveToDirection(Direction.RIGHT, 1, 0);
    }

    public void testMoveToDown() {
        character.moveDown();

        testMoveToDirection(Direction.DOWN, 0, 1);
    }

    public void testMoveToLeft() {
        character.moveLeft();

        testMoveToDirection(Direction.LEFT, -1, 0);
    }

    private void testMoveToDirection(
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
        final GameCharacter characterTest,
        final byte direction,
        final int initialPositionX,
        final int initialPositionY,
        final int newPositionX,
        final int newPositionY
    );
}

package org.rpgrunner.test.helper;

import org.rpgrunner.game.Direction;
import org.rpgrunner.game.character.GameCharacter;

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
        character = new GameCharacter(null);
        testMoveToUp();
    }

    private void testNewCharacterMoveToRight() {
        character = new GameCharacter(null);
        testMoveToRight();
    }

    private void testNewCharacterMoveToDown() {
        character = new GameCharacter(null);
        testMoveToDown();
    }

    private void testNewCharacterMoveToLeft() {
        character = new GameCharacter(null);
        testMoveToLeft();
    }

    public void testMoveToUp() {
        byte direction = Direction.UP;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY - 1;

        character.moveUp();

        testMoviment(
            character,
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public void testMoveToRight() {
        byte direction = Direction.RIGHT;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX + 1;
        int newPositionY = initialPositionY;

        character.moveRight();

        testMoviment(
            character,
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public void testMoveToDown() {
        byte direction = Direction.DOWN;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY + 1;

        character.moveDown();

        testMoviment(
            character,
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public void testMoveToLeft() {
        byte direction = Direction.LEFT;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX - 1;
        int newPositionY = initialPositionY;

        character.moveLeft();

        testMoviment(
            character,
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public abstract void testMoviment(
        final GameCharacter characterTest,
        final byte direction,
        final int initialPositionX,
        final int initialPositionY,
        final int newPositionX,
        final int newPositionY
    );
}

package org.rpgrunner.test.helper;

import java.util.Random;

import org.rpgrunner.test.mock.MapSpy;
import org.rpgrunner.test.mock.CharacterSpy;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.CollisionDetector;

public abstract class TestCollisionAllDirections {
    private final CollisionDetector collisionDetector;
    private final int additionalX;
    private final int additionalY;

    public TestCollisionAllDirections(
        final CollisionDetector collisionDetectorTest,
        final int additionalXTest,
        final int additionalYTest
    ) {
        collisionDetector = collisionDetectorTest;
        additionalX = additionalXTest;
        additionalY = additionalYTest;
    }

    public void test() {
        testCollisionCharacterStopped();
        testCollisionCharacterMoveUp();
        testCollisionCharacterMoveRight();
        testCollisionCharacterMoveDown();
        testCollisionCharacterMoveLeft();
    }

    private void testCollisionCharacterStopped() {
        CharacterSpy[] characters = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characters);
        moveCharacter(character);
        int initialPositionX = character.getMapPositionX() + additionalX;
        int initialPositionY = character.getMapPositionY() + additionalY;
        CharacterSpy collisionCharacter = getCollisionCharacter(
            characters,
            character,
            initialPositionX,
            initialPositionY
        );

        testOperation(character);
    }

    private void testCollisionCharacterMoveUp() {
        CharacterSpy[] characters = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characters);
        moveCharacter(character);
        int initialPositionX = character.getMapPositionX() + additionalX;
        int initialPositionY = calculateInitialPositionMoveAxis(
            character.getDirection(),
            Direction.UP,
            character.getMapPositionY(),
            additionalY,
            1
        );
        CharacterSpy collisionCharacter = getCollisionCharacter(
            characters,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveUp();
        testOperation(character);
    }

    private void testCollisionCharacterMoveRight() {
        CharacterSpy[] characters = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characters);
        moveCharacter(character);
        int initialPositionX = calculateInitialPositionMoveAxis(
            character.getDirection(),
            Direction.RIGHT,
            character.getMapPositionX(),
            additionalX,
            -1
        );
        int initialPositionY = character.getMapPositionY() + additionalY;
        CharacterSpy collisionCharacter = getCollisionCharacter(
            characters,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveRight();
        testOperation(character);
    }

    private void testCollisionCharacterMoveDown() {
        CharacterSpy[] characters = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characters);
        moveCharacter(character);
        int initialPositionX = character.getMapPositionX() + additionalX;
        int initialPositionY = calculateInitialPositionMoveAxis(
            character.getDirection(),
            Direction.DOWN,
            character.getMapPositionY(),
            additionalY,
            -1
        );
        CharacterSpy collisionCharacter = getCollisionCharacter(
            characters,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveDown();
        testOperation(character);
    }

    private void testCollisionCharacterMoveLeft() {
        CharacterSpy[] characters = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characters);
        moveCharacter(character);
        int initialPositionX = calculateInitialPositionMoveAxis(
            character.getDirection(),
            Direction.LEFT,
            character.getMapPositionX(),
            additionalX,
            1
        );
        int initialPositionY = character.getMapPositionY() + additionalY;
        CharacterSpy collisionCharacter = getCollisionCharacter(
            characters,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveLeft();
        testOperation(character);
    }

    private int calculateInitialPositionMoveAxis(
        final byte direction,
        final byte collisionCharacterDirection,
        final int characterPosition,
        final int additional,
        final int directionAdditional
    ) {
        byte invertedDirection = Direction.invertDirection(direction);

        if (direction == collisionCharacterDirection) {
            return (
                characterPosition
                + (additional * 2)
                + (directionAdditional * 2)
            );
        } else if (invertedDirection == collisionCharacterDirection) {
            return (characterPosition + (additional * 2));
        } else {
            return (characterPosition + additional + directionAdditional);
        }
    }

    private CharacterSpy[] generateCommonScenario() {
        Random random = new Random();
        MapSpy map = new MapSpy();
        CharacterSpy[] characters = RandomGenerator.generateRandomCharacters();
        collisionDetector.setMap(map);
        collisionDetector.setCharacters(characters);

        map.setCanMoveTo(true);

        return characters;
    }

    private CharacterSpy getCharacterTest(final CharacterSpy[] characters) {
        CharacterSpy character = RandomGenerator.getRandomCharacter(characters);

        Random random = new Random();
        int x = random.nextInt(100) + 3;
        int y = random.nextInt(100) + 3;
        character.setInitialPosition(x, y);

        return character;
    }

    private CharacterSpy getCollisionCharacter(
        final CharacterSpy[] characters,
        final CharacterSpy character,
        final int initialPositionX,
        final int initialPositionY
    ) {
        CharacterSpy collisionCharacter;

        do {
            collisionCharacter = RandomGenerator.getRandomCharacter(characters);
        } while (collisionCharacter == character);

        collisionCharacter.setInitialPosition(
            initialPositionX,
            initialPositionY
        );

        return collisionCharacter;
    }

    public abstract void moveCharacter(GameCharacter characterTest);

    public abstract void testOperation(GameCharacter characterTest);
}

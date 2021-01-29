package org.rpgrunner.test.helper;

import java.util.Random;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.CharacterSpy;
import org.rpgrunner.test.mock.MapSpy;

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
        CharacterElement[] characterElements = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characterElements);
        moveCharacter(character);
        int initialPositionX = character.getMapPositionX() + additionalX;
        int initialPositionY = character.getMapPositionY() + additionalY;
        CharacterSpy collisionCharacter = getCollisionCharacter(
            characterElements,
            character,
            initialPositionX,
            initialPositionY
        );

        testOperation(character);
    }

    private void testCollisionCharacterMoveUp() {
        CharacterElement[] characterElements = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characterElements);
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
            characterElements,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveUp();
        testOperation(character);
    }

    private void testCollisionCharacterMoveRight() {
        CharacterElement[] characterElements = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characterElements);
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
            characterElements,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveRight();
        testOperation(character);
    }

    private void testCollisionCharacterMoveDown() {
        CharacterElement[] characterElements = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characterElements);
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
            characterElements,
            character,
            initialPositionX,
            initialPositionY
        );

        collisionCharacter.moveDown();
        testOperation(character);
    }

    private void testCollisionCharacterMoveLeft() {
        CharacterElement[] characterElements = generateCommonScenario();
        CharacterSpy character = getCharacterTest(characterElements);
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
            characterElements,
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

    private CharacterElement[] generateCommonScenario() {
        MapSpy map = new MapSpy();
        CharacterElement[] characterElements = (
            RandomGenerator.generateRandomCharacterElements()
        );
        collisionDetector.setMap(map);
        collisionDetector.setCharacterElements(characterElements);

        map.setCanMoveTo(true);

        return characterElements;
    }

    private CharacterSpy getCharacterTest(
        final CharacterElement[] characterElements
    ) {
        CharacterElement characterElement = (
            RandomGenerator.getRandomCharacterElement(characterElements)
        );
        CharacterSpy character = (CharacterSpy) characterElement.getCharacter();

        Random random = new Random();
        int x = random.nextInt(100) + 3;
        int y = random.nextInt(100) + 3;
        character.setInitialPosition(x, y);

        return character;
    }

    private CharacterSpy getCollisionCharacter(
        final CharacterElement[] characterElements,
        final CharacterSpy character,
        final int initialPositionX,
        final int initialPositionY
    ) {
        CharacterSpy collisionCharacter;

        do {
            CharacterElement collisionCharacterElement = (
                RandomGenerator.getRandomCharacterElement(characterElements)
            );
            collisionCharacter = (
                (CharacterSpy) collisionCharacterElement.getCharacter()
            );
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

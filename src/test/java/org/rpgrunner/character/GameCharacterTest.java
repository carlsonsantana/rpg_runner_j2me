package org.rpgrunner.character;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterElementSpy;

public class GameCharacterTest extends TestCase {
    private static int TEST_REPEAT_LOOP = 100;
    private CharacterElementSpy characterElement;
    private GameCharacter character;
    private String characterBaseName;
    private Random random;

    public GameCharacterTest() {
        random = new Random();
    }

    public void setUp() {
        characterBaseName = RandomGenerator.getRandomString();
        character = new GameCharacter(characterBaseName);
        characterElement = new CharacterElementSpy();
        character.setCharacterElement(characterElement);
    }

    public void testReturnSameFileBaseName() {
        Assert.assertEquals(characterBaseName, character.getFileBaseName());
    }

    public void testCharacterStartStopped() {
        Assert.assertFalse(character.isMoving());
        Assert.assertFalse(characterElement.isOnMoveCalled());
    }

    public void testFinishMoveWhenCharacterIsStopped() {
        byte direction = character.getDirection();
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();

        checkChangePositionWhenFinishMovement(
            direction,
            initialPositionX,
            initialPositionY
        );
    }

    public void testMoveWhenAnimationIsCompleted() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkMoveWhenAnimationIsCompleted();
        }
    }

    private void checkMoveWhenAnimationIsCompleted() {
        byte direction = RandomGenerator.getRandomDirection();
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = getNewPositionX(direction, initialPositionX);
        int newPositionY = getNewPositionY(direction, initialPositionY);

        moveByDirection(direction);

        Assert.assertTrue(character.isMoving());
        Assert.assertEquals(direction, character.getDirection());
        Assert.assertEquals(initialPositionX, character.getMapPositionX());
        Assert.assertEquals(initialPositionY, character.getMapPositionY());
        Assert.assertEquals(newPositionX, character.getMapNextPositionX());
        Assert.assertEquals(newPositionY, character.getMapNextPositionY());

        checkChangePositionWhenFinishMovement(
            direction,
            newPositionX,
            newPositionY
        );
    }

    public void testOnlyExecuteNewMoveWhenMoveIsFinished() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkOnlyExecuteNewMoveWhenMoveIsFinished();
        }
    }

    private void checkOnlyExecuteNewMoveWhenMoveIsFinished() {
        byte direction = RandomGenerator.getRandomDirection();
        byte newDirection = RandomGenerator.getRandomDirection();
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = getNewPositionX(direction, initialPositionX);
        int newPositionY = getNewPositionY(direction, initialPositionY);

        moveByDirection(direction);
        moveByDirection(newDirection);

        checkChangePositionWhenFinishMovement(
            direction,
            newPositionX,
            newPositionY
        );
    }

    private int getNewPositionX(final byte direction, final int positionX) {
        switch (direction) {
            case Direction.RIGHT:
                return positionX + 1;
            case Direction.LEFT:
                return positionX - 1;
            default:
                return positionX;
        }
    }

    private int getNewPositionY(final byte direction, final int positionY) {
        switch (direction) {
            case Direction.UP:
                return positionY - 1;
            case Direction.DOWN:
                return positionY + 1;
            default:
                return positionY;
        }
    }

    public void testCancelMove() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCancelMove();
        }
    }

    private void checkCancelMove() {
        byte direction = RandomGenerator.getRandomDirection();
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();

        moveByDirection(direction);
        character.cancelMove();

        checkCharacterStoppedPosition(
            direction,
            initialPositionX,
            initialPositionY
        );
        checkChangePositionWhenFinishMovement(
            direction,
            initialPositionX,
            initialPositionY
        );
    }

    private void moveByDirection(final byte direction) {
        switch (direction) {
            case Direction.UP:
                character.moveUp();
                break;
            case Direction.RIGHT:
                character.moveRight();
                break;
            case Direction.DOWN:
                character.moveDown();
                break;
            default:
                character.moveLeft();
        }
    }

    private void checkChangePositionWhenFinishMovement(
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
        character.finishMove();

        checkCharacterStoppedPosition(
            direction,
            finalPositionX,
            finalPositionY
        );
    }

    private void checkCharacterStoppedPosition(
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
        Assert.assertFalse(character.isMoving());
        Assert.assertEquals(direction, character.getDirection());
        Assert.assertEquals(finalPositionX, character.getMapPositionX());
        Assert.assertEquals(finalPositionY, character.getMapPositionY());
        Assert.assertEquals(finalPositionX, character.getMapNextPositionX());
        Assert.assertEquals(finalPositionY, character.getMapNextPositionY());
    }

    public void testOnMoveCalledWhenMoveUp() {
        character.moveUp();
        Assert.assertTrue(characterElement.isOnMoveCalled());
    }

    public void testOnMoveCalledWhenMoveRight() {
        character.moveRight();
        Assert.assertTrue(characterElement.isOnMoveCalled());
    }

    public void testOnMoveCalledWhenMoveDown() {
        character.moveDown();
        Assert.assertTrue(characterElement.isOnMoveCalled());
    }

    public void testOnMoveCalledWhenMoveLeft() {
        character.moveLeft();
        Assert.assertTrue(characterElement.isOnMoveCalled());
    }

    public void testSetMapPosition() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            int mapPositionX = random.nextInt(1000);
            int mapPositionY = random.nextInt(1000);

            character.setMapPosition(mapPositionX, mapPositionY);

            Assert.assertEquals(mapPositionX, character.getMapPositionX());
            Assert.assertEquals(mapPositionY, character.getMapPositionY());
            Assert.assertEquals(mapPositionX, character.getMapNextPositionX());
            Assert.assertEquals(mapPositionY, character.getMapNextPositionY());
        }
    }
}

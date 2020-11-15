package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.game.Direction;
import org.rpgrunner.test.helper.RandomGenerator;

public class GameCharacterTest extends TestCase {
    private GameCharacter character;

    private abstract class TestOnlyExecuteNewMoveWhenLastMoveIsFinished {
        public abstract void moveToDirection();

        public void test(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            testNewMoveToUpDirection(direction, finalPositionX, finalPositionY);

            testNewMoveToRightDirection(
                direction,
                finalPositionX,
                finalPositionY
            );

            testNewMoveToDownDirection(
                direction,
                finalPositionX,
                finalPositionY
            );

            testNewMoveToLeftDirection(
                direction,
                finalPositionX,
                finalPositionY
            );
        }

        private void testNewMoveToUpDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveUp();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }

        private void testNewMoveToRightDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveRight();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }

        private void testNewMoveToDownDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveDown();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }

        private void testNewMoveToLeftDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveLeft();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }
    }

    public void setUp() {
        character = new GameCharacter(null);
    }

    public void testReturnSameFileBaseName() {
        String randomFileBaseName = RandomGenerator.getRandomString();
        character = new GameCharacter(randomFileBaseName);
        Assert.assertEquals(
            randomFileBaseName,
            character.getFileBaseName()
        );
    }

    public void testCharacterStartStopped() {
        Assert.assertFalse(character.isMoving());
    }

    public void testFinishMoveWhenCharacterIsStopped() {
        byte direction = character.getDirection();
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();

        testFinishMoviment(direction, initialPositionX, initialPositionY);
    }

    public void testMoveToUpWhenAnimationIsCompleted() {
        byte direction = Direction.UP;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY - 1;

        character.moveUp();

        testMoviment(
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public void testMoveToRightWhenAnimationIsCompleted() {
        byte direction = Direction.RIGHT;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX + 1;
        int newPositionY = initialPositionY;

        character.moveRight();

        testMoviment(
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public void testMoveToDownWhenAnimationIsCompleted() {
        byte direction = Direction.DOWN;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY + 1;

        character.moveDown();

        testMoviment(
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    public void testMoveToLeftWhenAnimationIsCompleted() {
        byte direction = Direction.LEFT;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX - 1;
        int newPositionY = initialPositionY;

        character.moveLeft();

        testMoviment(
            direction,
            initialPositionX,
            initialPositionY,
            newPositionX,
            newPositionY
        );
    }

    private void testMoviment(
        final byte direction,
        final int initialPositionX,
        final int initialPositionY,
        final int newPositionX,
        final int newPositionY
    ) {
        Assert.assertTrue(character.isMoving());
        Assert.assertEquals(direction, character.getDirection());
        Assert.assertEquals(initialPositionX, character.getMapPositionX());
        Assert.assertEquals(initialPositionY, character.getMapPositionY());
        Assert.assertEquals(newPositionX, character.getMapNextPositionX());
        Assert.assertEquals(newPositionY, character.getMapNextPositionY());

        testFinishMoviment(direction, newPositionX, newPositionY);
    }

    private void testFinishMoviment(
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
        character.finishMove();

        Assert.assertFalse(character.isMoving());
        Assert.assertEquals(direction, character.getDirection());
        Assert.assertEquals(finalPositionX, character.getMapPositionX());
        Assert.assertEquals(finalPositionY, character.getMapPositionY());
        Assert.assertEquals(
            finalPositionX,
            character.getMapNextPositionX()
        );
        Assert.assertEquals(
            finalPositionY,
            character.getMapNextPositionY()
        );
    }

    public void testOnlyExecuteNewMoveWhenMoveToUpIsFinished() {
        byte direction = Direction.UP;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY - 1;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                character = new GameCharacter(null);
                character.moveUp();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }

    public void testOnlyExecuteNewMoveWhenMoveToRightIsFinished() {
        byte direction = Direction.RIGHT;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX + 1;
        int newPositionY = initialPositionY;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                character = new GameCharacter(null);
                character.moveRight();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }

    public void testOnlyExecuteNewMoveWhenMoveToDownIsFinished() {
        byte direction = Direction.DOWN;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY + 1;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                character = new GameCharacter(null);
                character.moveDown();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }

    public void testOnlyExecuteNewMoveWhenMoveToLeftIsFinished() {
        byte direction = Direction.LEFT;
        int initialPositionX = character.getMapPositionX();
        int initialPositionY = character.getMapPositionY();
        int newPositionX = initialPositionX - 1;
        int newPositionY = initialPositionY;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                character = new GameCharacter(null);
                character.moveLeft();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }
}

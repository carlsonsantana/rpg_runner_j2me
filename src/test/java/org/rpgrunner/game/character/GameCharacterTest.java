package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.game.Direction;
import org.rpgrunner.test.helper.RandomGenerator;

public class GameCharacterTest extends TestCase {
    private GameCharacter gameCharacter;

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
            gameCharacter.moveUp();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }

        private void testNewMoveToRightDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            gameCharacter.moveRight();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }

        private void testNewMoveToDownDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            gameCharacter.moveDown();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }

        private void testNewMoveToLeftDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            gameCharacter.moveLeft();
            testFinishMoviment(direction, finalPositionX, finalPositionY);
        }
    }

    public void setUp() {
        gameCharacter = new GameCharacter(null);
    }

    public void testReturnSameFileBaseName() {
        String randomFileBaseName = RandomGenerator.getRandomString();
        gameCharacter = new GameCharacter(randomFileBaseName);
        Assert.assertEquals(
            randomFileBaseName,
            gameCharacter.getFileBaseName()
        );
    }

    public void testCharacterStartStopped() {
        Assert.assertFalse(gameCharacter.isMoving());
    }

    public void testFinishMoveWhenCharacterIsStopped() {
        byte direction = gameCharacter.getDirection();
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();

        testFinishMoviment(direction, initialPositionX, initialPositionY);
    }

    public void testMoveToUpWhenAnimationIsCompleted() {
        byte direction = Direction.UP;
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY - 1;

        gameCharacter.moveUp();

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
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX + 1;
        int newPositionY = initialPositionY;

        gameCharacter.moveRight();

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
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY + 1;

        gameCharacter.moveDown();

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
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX - 1;
        int newPositionY = initialPositionY;

        gameCharacter.moveLeft();

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
        Assert.assertTrue(gameCharacter.isMoving());
        Assert.assertEquals(direction, gameCharacter.getDirection());
        Assert.assertEquals(initialPositionX, gameCharacter.getMapPositionX());
        Assert.assertEquals(initialPositionY, gameCharacter.getMapPositionY());
        Assert.assertEquals(newPositionX, gameCharacter.getMapNextPositionX());
        Assert.assertEquals(newPositionY, gameCharacter.getMapNextPositionY());

        testFinishMoviment(direction, newPositionX, newPositionY);
    }

    private void testFinishMoviment(
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
        gameCharacter.finishMove();

        Assert.assertFalse(gameCharacter.isMoving());
        Assert.assertEquals(direction, gameCharacter.getDirection());
        Assert.assertEquals(finalPositionX, gameCharacter.getMapPositionX());
        Assert.assertEquals(finalPositionY, gameCharacter.getMapPositionY());
        Assert.assertEquals(
            finalPositionX,
            gameCharacter.getMapNextPositionX()
        );
        Assert.assertEquals(
            finalPositionY,
            gameCharacter.getMapNextPositionY()
        );
    }

    public void testOnlyExecuteNewMoveWhenMoveToUpIsFinished() {
        byte direction = Direction.UP;
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY - 1;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                gameCharacter = new GameCharacter(null);
                gameCharacter.moveUp();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }

    public void testOnlyExecuteNewMoveWhenMoveToRightIsFinished() {
        byte direction = Direction.RIGHT;
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX + 1;
        int newPositionY = initialPositionY;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                gameCharacter = new GameCharacter(null);
                gameCharacter.moveRight();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }

    public void testOnlyExecuteNewMoveWhenMoveToDownIsFinished() {
        byte direction = Direction.DOWN;
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX;
        int newPositionY = initialPositionY + 1;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                gameCharacter = new GameCharacter(null);
                gameCharacter.moveDown();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }

    public void testOnlyExecuteNewMoveWhenMoveToLeftIsFinished() {
        byte direction = Direction.LEFT;
        int initialPositionX = gameCharacter.getMapPositionX();
        int initialPositionY = gameCharacter.getMapPositionY();
        int newPositionX = initialPositionX - 1;
        int newPositionY = initialPositionY;

        TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
        test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
            public void moveToDirection() {
                gameCharacter = new GameCharacter(null);
                gameCharacter.moveLeft();
            }
        };
        test.test(direction, newPositionX, newPositionY);
    }
}

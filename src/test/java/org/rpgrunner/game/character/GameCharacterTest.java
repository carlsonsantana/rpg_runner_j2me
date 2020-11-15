package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.game.Direction;
import org.rpgrunner.test.helper.RandomGenerator;

public class GameCharacterTest extends TestCase {
    private GameCharacter character;

    private abstract class TestAllDirections {
        public void test() {
            testMoveToUp();
            testMoveToRight();
            testMoveToDown();
            testMoveToLeft();
        }

        private void testMoveToUp() {
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

        private void testMoveToRight() {
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

        private void testMoveToDown() {
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

        private void testMoveToLeft() {
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

        public abstract void testMoviment(
            final byte direction,
            final int initialPositionX,
            final int initialPositionY,
            final int newPositionX,
            final int newPositionY
        );
    }

    private abstract class TestOnlyExecuteNewMoveWhenLastMoveIsFinished {
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
            testChangePositionWhenFinishMoviment(
                direction,
                finalPositionX,
                finalPositionY
            );
        }

        private void testNewMoveToRightDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveRight();
            testChangePositionWhenFinishMoviment(
                direction,
                finalPositionX,
                finalPositionY
            );
        }

        private void testNewMoveToDownDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveDown();
            testChangePositionWhenFinishMoviment(
                direction,
                finalPositionX,
                finalPositionY
            );
        }

        private void testNewMoveToLeftDirection(
            final byte direction,
            final int finalPositionX,
            final int finalPositionY
        ) {
            moveToDirection();
            character.moveLeft();
            testChangePositionWhenFinishMoviment(
                direction,
                finalPositionX,
                finalPositionY
            );
        }

        public abstract void moveToDirection();
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

        testChangePositionWhenFinishMoviment(
            direction,
            initialPositionX,
            initialPositionY
        );
    }

    public void testMoveWhenAnimationIsCompleted() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testChangePositionOnMoviment(
                    direction,
                    initialPositionX,
                    initialPositionY,
                    newPositionX,
                    newPositionY
                );
            }
        };
    }

    private void testChangePositionOnMoviment(
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

        testChangePositionWhenFinishMoviment(
            direction,
            newPositionX,
            newPositionY
        );
    }

    private void testChangePositionWhenFinishMoviment(
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

    public void testOnlyExecuteNewMoveWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                TestOnlyExecuteNewMoveWhenLastMoveIsFinished test;
                test = new TestOnlyExecuteNewMoveWhenLastMoveIsFinished() {
                    public void moveToDirection() {
                        character = new GameCharacter(null);
                        character.moveUp();
                    }
                };
                test.test(direction, newPositionX, newPositionY);
            }
        };
    }
}

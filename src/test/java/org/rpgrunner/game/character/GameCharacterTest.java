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

        public void testMoveToUp() {
            character = new GameCharacter(null);
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

        public void testMoveToRight() {
            character = new GameCharacter(null);
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

        public void testMoveToDown() {
            character = new GameCharacter(null);
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

        public void testMoveToLeft() {
            character = new GameCharacter(null);
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
        testAllDirections.test();
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

    public void testOnlyExecuteNewMoveToUpWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testNewMoveToUpDirection(
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    public void testOnlyExecuteNewMoveToRightWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testNewMoveToRightDirection(
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    public void testOnlyExecuteNewMoveToDownWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testNewMoveToDownDirection(
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    public void testOnlyExecuteNewMoveToLeftWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testNewMoveToLeftDirection(
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    private void testNewMoveToUpDirection(
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
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
        character.moveLeft();
        testChangePositionWhenFinishMoviment(
            direction,
            finalPositionX,
            finalPositionY
        );
    }
}

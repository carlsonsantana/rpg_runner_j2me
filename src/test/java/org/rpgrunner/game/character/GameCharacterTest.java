package org.rpgrunner.game.character;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.game.Direction;
import org.rpgrunner.test.helper.RandomGenerator;

public class GameCharacterTest extends TestCase {
    private GameCharacter character;

    private abstract class TestAllDirections {
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

        testCharacterStoppedPosition(direction, finalPositionX, finalPositionY);
    }

    private void testCharacterStoppedPosition(
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
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

    public void testMoveMoreThanOneTime() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testChangePositionWhenFinishMoviment(
                    direction,
                    newPositionX,
                    newPositionY
                );

                testRandomMoviment(direction);
            }

            private void testRandomMoviment(final byte direction) {
                Random random = new Random();
                int randomDirection = random.nextInt(
                    Direction.NUMBER_DIRECTIONS + 1
                );
                if (randomDirection == 1) {
                    testMoveToUp();
                } else if (randomDirection == 2) {
                    testMoveToRight();
                } else if (randomDirection == 3) {
                    testMoveToDown();
                } else if (randomDirection == 4) {
                    testMoveToLeft();
                }
            }
        };
        for (int i = 0, numberTests = 100; i < numberTests; i++) {
            testAllDirections.test();
        }
    }

    public void testCancelMove() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMoviment(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                character.cancelMove();
                testCharacterStoppedPosition(
                    direction,
                    initialPositionX,
                    initialPositionY
                );
                testChangePositionWhenFinishMoviment(
                    direction,
                    initialPositionX,
                    initialPositionY
                );
            }
        };
        testAllDirections.test();
    }
}

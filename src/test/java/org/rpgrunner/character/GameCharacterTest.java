package org.rpgrunner.character;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterElementSpy;

public class GameCharacterTest extends TestCase {
    private CharacterElementSpy characterElement;
    private GameCharacter character;
    private String characterBaseName;

    private abstract class TestAllDirections {
        public void test() {
            testMoveToUp();
            testMoveToRight();
            testMoveToDown();
            testMoveToLeft();
        }

        public void testMoveToUp() {
            generateCharacter();
            character.moveUp();

            testMoveToDirection(Direction.UP, 0, -1);
        }

        public void testMoveToRight() {
            generateCharacter();
            character.moveRight();

            testMoveToDirection(Direction.RIGHT, 1, 0);
        }

        public void testMoveToDown() {
            generateCharacter();
            character.moveDown();

            testMoveToDirection(Direction.DOWN, 0, 1);
        }

        public void testMoveToLeft() {
            generateCharacter();
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
                direction,
                initialPositionX,
                initialPositionY,
                newPositionX,
                newPositionY
            );
        }

        public abstract void testMovement(
            final byte direction,
            final int initialPositionX,
            final int initialPositionY,
            final int newPositionX,
            final int newPositionY
        );
    }

    public void setUp() {
        characterBaseName = RandomGenerator.getRandomString();
        generateCharacter();
    }

    private void generateCharacter() {
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

        testChangePositionWhenFinishMovement(
            direction,
            initialPositionX,
            initialPositionY
        );
    }

    public void testMoveWhenAnimationIsCompleted() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMovement(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                Assert.assertTrue(character.isMoving());
                Assert.assertEquals(direction, character.getDirection());
                Assert.assertEquals(
                    initialPositionX,
                    character.getMapPositionX()
                );
                Assert.assertEquals(
                    initialPositionY,
                    character.getMapPositionY()
                );
                Assert.assertEquals(
                    newPositionX,
                    character.getMapNextPositionX()
                );
                Assert.assertEquals(
                    newPositionY,
                    character.getMapNextPositionY()
                );

                testChangePositionWhenFinishMovement(
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    private void testChangePositionWhenFinishMovement(
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
        Assert.assertEquals(finalPositionX, character.getMapNextPositionX());
        Assert.assertEquals(finalPositionY, character.getMapNextPositionY());
    }

    public void testOnlyExecuteNewMoveToUpWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMovement(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                character.moveUp();
                testChangePositionWhenFinishMovement(
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
            public void testMovement(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                character.moveRight();
                testChangePositionWhenFinishMovement(
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
            public void testMovement(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                character.moveDown();
                testChangePositionWhenFinishMovement(
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
            public void testMovement(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                character.moveLeft();
                testChangePositionWhenFinishMovement(
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    public void testMoveMoreThanOneTime() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMovement(
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testChangePositionWhenFinishMovement(
                    direction,
                    newPositionX,
                    newPositionY
                );

                testRandomMovement(direction);
            }

            private void testRandomMovement(final byte direction) {
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
            public void testMovement(
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
                testChangePositionWhenFinishMovement(
                    direction,
                    initialPositionX,
                    initialPositionY
                );
            }
        };
        testAllDirections.test();
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
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
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

package org.rpgrunner.character;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.helper.TestAllDirections;
import org.rpgrunner.test.mock.character.CharacterElementSpy;

public class GameCharacterTest extends TestCase {
    private CharacterElementSpy characterElement;
    private GameCharacter character;
    private String characterBaseName;

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

        testChangePositionWhenFinishMovement(
            character,
            direction,
            initialPositionX,
            initialPositionY
        );
    }

    public void testMoveWhenAnimationIsCompleted() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMovement(
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                Assert.assertTrue(characterTest.isMoving());
                Assert.assertEquals(direction, characterTest.getDirection());
                Assert.assertEquals(
                    initialPositionX,
                    characterTest.getMapPositionX()
                );
                Assert.assertEquals(
                    initialPositionY,
                    characterTest.getMapPositionY()
                );
                Assert.assertEquals(
                    newPositionX,
                    characterTest.getMapNextPositionX()
                );
                Assert.assertEquals(
                    newPositionY,
                    characterTest.getMapNextPositionY()
                );

                testChangePositionWhenFinishMovement(
                    characterTest,
                    direction,
                    newPositionX,
                    newPositionY
                );
            }
        };
        testAllDirections.test();
    }

    private void testChangePositionWhenFinishMovement(
        final GameCharacter characterTest,
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
        characterTest.finishMove();

        testCharacterStoppedPosition(
            characterTest,
            direction,
            finalPositionX,
            finalPositionY
        );
    }

    private void testCharacterStoppedPosition(
        final GameCharacter characterTest,
        final byte direction,
        final int finalPositionX,
        final int finalPositionY
    ) {
        Assert.assertFalse(characterTest.isMoving());
        Assert.assertEquals(direction, characterTest.getDirection());
        Assert.assertEquals(finalPositionX, characterTest.getMapPositionX());
        Assert.assertEquals(finalPositionY, characterTest.getMapPositionY());
        Assert.assertEquals(
            finalPositionX,
            characterTest.getMapNextPositionX()
        );
        Assert.assertEquals(
            finalPositionY,
            characterTest.getMapNextPositionY()
        );
    }

    public void testOnlyExecuteNewMoveToUpWhenMoveIsFinished() {
        TestAllDirections testAllDirections = new TestAllDirections() {
            public void testMovement(
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                characterTest.moveUp();
                testChangePositionWhenFinishMovement(
                    characterTest,
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
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                characterTest.moveRight();
                testChangePositionWhenFinishMovement(
                    characterTest,
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
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                characterTest.moveDown();
                testChangePositionWhenFinishMovement(
                    characterTest,
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
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                characterTest.moveLeft();
                testChangePositionWhenFinishMovement(
                    characterTest,
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
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                testChangePositionWhenFinishMovement(
                    characterTest,
                    direction,
                    newPositionX,
                    newPositionY
                );

                testRandomMovement(characterTest, direction);
            }

            private void testRandomMovement(
                final GameCharacter characterTest,
                final byte direction
            ) {
                Random random = new Random();
                int randomDirection = random.nextInt(
                    Direction.NUMBER_DIRECTIONS + 1
                );

                if (randomDirection == 1) {
                    testMoveToUp(characterTest);
                } else if (randomDirection == 2) {
                    testMoveToRight(characterTest);
                } else if (randomDirection == 3) {
                    testMoveToDown(characterTest);
                } else if (randomDirection == 4) {
                    testMoveToLeft(characterTest);
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
                final GameCharacter characterTest,
                final byte direction,
                final int initialPositionX,
                final int initialPositionY,
                final int newPositionX,
                final int newPositionY
            ) {
                characterTest.cancelMove();
                testCharacterStoppedPosition(
                    characterTest,
                    direction,
                    initialPositionX,
                    initialPositionY
                );
                testChangePositionWhenFinishMovement(
                    characterTest,
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

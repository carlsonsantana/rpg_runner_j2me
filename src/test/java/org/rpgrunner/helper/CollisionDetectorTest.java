package org.rpgrunner.helper;

import java.util.Random;
import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class CollisionDetectorTest extends TestCase {
    private static int TEST_REPEAT_LOOP = 100;
    private CollisionDetector collisionDetector;

    private abstract class TestCollisionAllDirections {
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
            Vector characterElements = generateCommonScenario();
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
            Vector characterElements = generateCommonScenario();
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
            Vector characterElements = generateCommonScenario();
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
            Vector characterElements = generateCommonScenario();
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
            Vector characterElements = generateCommonScenario();
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

        private Vector generateCommonScenario() {
            MapSpy map = new MapSpy();
            Vector characterElements = (
                RandomGenerator.generateRandomCharacterElements()
            );
            collisionDetector.setMap(map);
            collisionDetector.setCharacterElements(characterElements);

            map.setCanMoveTo(true);

            return characterElements;
        }

        private CharacterSpy getCharacterTest(final Vector characterElements) {
            CharacterElement characterElement = (
                RandomGenerator.getRandomCharacterElement(characterElements)
            );
            CharacterSpy character =
                (CharacterSpy) characterElement.getCharacter();

            Random random = new Random();
            int x = random.nextInt(100) + 3;
            int y = random.nextInt(100) + 3;
            character.setInitialPosition(x, y);

            return character;
        }

        private CharacterSpy getCollisionCharacter(
            final Vector characterElements,
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

    public void setUp() {
        collisionDetector = new CollisionDetector();
    }

    public void testCantMoveWhenExistsAMapCollision() {
        MapSpy map = new MapSpy();
        Vector characterElements = (
            RandomGenerator.generateRandomCharacterElements()
        );

        collisionDetector.setMap(map);
        collisionDetector.setCharacterElements(characterElements);

        map.setCanMoveTo(false);
        CharacterElement characterElement = (
            RandomGenerator.getRandomCharacterElement(characterElements)
        );
        GameCharacter character = characterElement.getCharacter();

        Assert.assertFalse(collisionDetector.canMove(character));
    }

    public void testCantMoveUpWhenExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveUpWhenExistsACharacterCollision();
        }
    }

    private void checkCantMoveUpWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;
        test = new TestCollisionAllDirections(collisionDetector, 0, -1) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveUp();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertFalse(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCantMoveRightWhenExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveRightWhenExistsACharacterCollision();
        }
    }

    private void checkCantMoveRightWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, 1, 0) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveRight();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertFalse(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCantMoveDownWhenExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveDownWhenExistsACharacterCollision();
        }
    }

    private void checkCantMoveDownWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, 0, 1) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveDown();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertFalse(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCantMoveLeftWhenExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveLeftWhenExistsACharacterCollision();
        }
    }

    private void checkCantMoveLeftWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, -1, 0) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveLeft();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertFalse(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCanMoveUpWhenNotExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveUpWhenNotExistsACharacterCollision();
        }
    }

    private void checkCanMoveUpWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, 0, -2) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveUp();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertTrue(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCanMoveRightWhenNotExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveRightWhenNotExistsACharacterCollision();
        }
    }

    private void checkCanMoveRightWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, 2, 0) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveRight();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertTrue(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCanMoveDownWhenNotExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveDownWhenNotExistsACharacterCollision();
        }
    }

    private void checkCanMoveDownWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, 0, 2) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveDown();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertTrue(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }

    public void testCanMoveLeftWhenNotExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveLeftWhenNotExistsACharacterCollision();
        }
    }

    private void checkCanMoveLeftWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        test = new TestCollisionAllDirections(collisionDetector, -2, 0) {
            public void moveCharacter(final GameCharacter characterTest) {
                characterTest.moveLeft();
            }

            public void testOperation(final GameCharacter characterTest) {
                Assert.assertTrue(collisionDetector.canMove(characterTest));
            }
        };
        test.test();
    }
}

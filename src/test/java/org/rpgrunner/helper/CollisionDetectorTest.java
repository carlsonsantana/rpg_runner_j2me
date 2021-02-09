package org.rpgrunner.helper;

import java.util.Random;
import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class CollisionDetectorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final byte STOPPED_DIRECTION = (byte) 0;
    private CollisionDetector collisionDetector;
    private MapSpy map;
    private Vector characterElements;
    private CharacterSpy character;
    private CharacterSpy collisionCharacter;

    private abstract class TestCollisionAllDirections {
        private final int additional;

        TestCollisionAllDirections(final int additionalTest) {
            additional = additionalTest;
        }

        public void test() {
            testCollisionCharacterStopped();
            testCollisionCharacterMoveUp();
            testCollisionCharacterMoveRight();
            testCollisionCharacterMoveDown();
            testCollisionCharacterMoveLeft();
        }

        private void testCollisionCharacterStopped() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(STOPPED_DIRECTION);

            testOperation();
        }

        private void testCollisionCharacterMoveUp() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.UP);

            collisionCharacter.moveUp();
            testOperation();
        }

        private void testCollisionCharacterMoveRight() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.RIGHT);

            collisionCharacter.moveRight();
            testOperation();
        }

        private void testCollisionCharacterMoveDown() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.DOWN);

            collisionCharacter.moveDown();
            testOperation();
        }

        private void testCollisionCharacterMoveLeft() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.LEFT);

            collisionCharacter.moveLeft();
            testOperation();
        }

        private void setInitialPosition(final byte direction) {
            int additionalXValue = getAdditionalXValue();
            int additionalXCollisionCharacterValue = (
                getDirectionAdditionalX(direction)
            );
            int additionalYValue = getAdditionalYValue();
            int additionalYCollisionCharacterValue = (
                getDirectionAdditionalY(direction)
            );
            int initialPositionX = calculateInitialPosition(
                direction,
                character.getMapPositionX(),
                additionalXValue,
                additionalXCollisionCharacterValue
            );
            int initialPositionY = calculateInitialPosition(
                direction,
                character.getMapPositionY(),
                additionalYValue,
                additionalYCollisionCharacterValue
            );

            collisionCharacter.setInitialPosition(
                initialPositionX,
                initialPositionY
            );
        }

        private int getAdditionalXValue() {
            return (
                additional
                * getDirectionAdditionalX(character.getDirection())
            );
        }

        private int getDirectionAdditionalX(final byte direction) {
            switch (direction) {
                case Direction.RIGHT:
                    return 1;
                case Direction.LEFT:
                    return -1;
                default:
                    return 0;
            }
        }

        private int getAdditionalYValue() {
            return (
                additional
                * getDirectionAdditionalY(character.getDirection())
            );
        }

        private int getDirectionAdditionalY(final byte direction) {
            switch (direction) {
                case Direction.DOWN:
                    return 1;
                case Direction.UP:
                    return -1;
                default:
                    return 0;
            }
        }

        private int calculateInitialPosition(
            final byte collisionCharacterDirection,
            final int characterPosition,
            final int additionalValue,
            final int additionalCollisionCharacterValue
        ) {
            byte direction = character.getDirection();
            byte invertedDirection = Direction.invertDirection(direction);

            if (direction == collisionCharacterDirection) {
                return (
                    characterPosition
                    + (additionalValue * 2)
                    - (additionalCollisionCharacterValue * 2)
                );
            }

            if (invertedDirection == collisionCharacterDirection) {
                return (characterPosition + (additionalValue * 2));
            }

            return (
                characterPosition
                + additionalValue
                - additionalCollisionCharacterValue
            );
        }

        public abstract void moveCharacter();

        public abstract void testOperation();
    }

    public void setUp() {
        collisionDetector = new CollisionDetector();
        map = new MapSpy();
        map.setCanMoveTo(true);
        collisionDetector.setMap(map);
        generateNewScenario();
    }

    private void generateNewScenario() {
        characterElements = (
            RandomGenerator.generateRandomCharacterElements()
        );
        collisionDetector.setCharacterElements(characterElements);
        character = getCharacterTest();
        collisionCharacter = getCollisionCharacter();
    }

    private CharacterSpy getCharacterTest() {
        CharacterElement characterElement = (
            RandomGenerator.getRandomCharacterElement(characterElements)
        );
        CharacterSpy newCharacter = (
            (CharacterSpy) characterElement.getCharacter()
        );

        Random random = new Random();
        int x = random.nextInt(100) + 3;
        int y = random.nextInt(100) + 3;
        newCharacter.setInitialPosition(x, y);

        return newCharacter;
    }

    private CharacterSpy getCollisionCharacter() {
        CharacterSpy newCollisionCharacter;

        do {
            CharacterElement collisionCharacterElement = (
                RandomGenerator.getRandomCharacterElement(characterElements)
            );
            newCollisionCharacter = (
                (CharacterSpy) collisionCharacterElement.getCharacter()
            );
        } while (newCollisionCharacter == character);

        return newCollisionCharacter;
    }

    public void testCantMoveWhenExistsAMapCollision() {
        map.setCanMoveTo(false);

        Assert.assertFalse(collisionDetector.canMove(character));
    }

    public void testCantMoveUpWhenExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveUpWhenExistsACharacterCollision();
        }
    }

    private void checkCantMoveUpWhenExistsACharacterCollision() {
        TestCollisionAllDirections test = new TestCollisionAllDirections(1) {
            public void moveCharacter() {
                character.moveUp();
            }

            public void testOperation() {
                Assert.assertFalse(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(1) {
            public void moveCharacter() {
                character.moveRight();
            }

            public void testOperation() {
                Assert.assertFalse(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(1) {
            public void moveCharacter() {
                character.moveDown();
            }

            public void testOperation() {
                Assert.assertFalse(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(1) {
            public void moveCharacter() {
                character.moveLeft();
            }

            public void testOperation() {
                Assert.assertFalse(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(2) {
            public void moveCharacter() {
                character.moveUp();
            }

            public void testOperation() {
                Assert.assertTrue(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(2) {
            public void moveCharacter() {
                character.moveRight();
            }

            public void testOperation() {
                Assert.assertTrue(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(2) {
            public void moveCharacter() {
                character.moveDown();
            }

            public void testOperation() {
                Assert.assertTrue(collisionDetector.canMove(character));
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
        TestCollisionAllDirections test = new TestCollisionAllDirections(2) {
            public void moveCharacter() {
                character.moveLeft();
            }

            public void testOperation() {
                Assert.assertTrue(collisionDetector.canMove(character));
            }
        };
        test.test();
    }
}

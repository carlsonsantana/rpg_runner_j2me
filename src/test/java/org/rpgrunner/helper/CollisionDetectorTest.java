package org.rpgrunner.helper;

import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.helper.TestCollisionAllDirections;
import org.rpgrunner.test.mock.map.MapSpy;

public class CollisionDetectorTest extends TestCase {
    private static int TEST_REPEAT_LOOP = 100;
    private CollisionDetector collisionDetector;

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

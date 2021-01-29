package org.rpgrunner.helper;

import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.helper.TestCollisionAllDirections;
import org.rpgrunner.test.mock.MapSpy;

public class CollisionDetectorTest extends TestCase {
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

    public void testCantMoveUpWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
            test = new TestCollisionAllDirections(collisionDetector, 0, -1) {
                public void moveCharacter(final GameCharacter characterTest) {
                    characterTest.moveUp();
                }

                public void testOperation(final GameCharacter characterTest) {
                    Assert.assertFalse(
                        collisionDetector.canMove(characterTest)
                    );
                }
            };
            test.test();
        }
    }

    public void testCantMoveRightWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
            test = new TestCollisionAllDirections(collisionDetector, 1, 0) {
                public void moveCharacter(final GameCharacter characterTest) {
                    characterTest.moveRight();
                }

                public void testOperation(final GameCharacter characterTest) {
                    Assert.assertFalse(
                        collisionDetector.canMove(characterTest)
                    );
                }
            };
            test.test();
        }
    }

    public void testCantMoveDownWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
            test = new TestCollisionAllDirections(collisionDetector, 0, 1) {
                public void moveCharacter(final GameCharacter characterTest) {
                    characterTest.moveDown();
                }

                public void testOperation(final GameCharacter characterTest) {
                    Assert.assertFalse(
                        collisionDetector.canMove(characterTest)
                    );
                }
            };
            test.test();
        }
    }

    public void testCantMoveLeftWhenExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
            test = new TestCollisionAllDirections(collisionDetector, -1, 0) {
                public void moveCharacter(final GameCharacter characterTest) {
                    characterTest.moveLeft();
                }

                public void testOperation(final GameCharacter characterTest) {
                    Assert.assertFalse(
                        collisionDetector.canMove(characterTest)
                    );
                }
            };
            test.test();
        }
    }

    public void testCanMoveUpWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
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
    }

    public void testCanMoveRightWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
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
    }

    public void testCanMoveDownWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
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
    }

    public void testCanMoveLeftWhenNotExistsACharacterCollision() {
        TestCollisionAllDirections test;

        for (int i = 0; i < 100; i++) {
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
}

package org.rpgrunner.helper;

import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.controller.GameControllerSpy;
import org.rpgrunner.test.mock.event.action.CharacterActionSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class MapHelperTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final byte STOPPED_DIRECTION = (byte) 0;
    private static final int MINIMUM_POSITION = 3;
    private MapHelper mapHelper;
    private MapSpy map;
    private Vector characterElements;
    private CharacterElement characterElement;
    private CharacterSpy character;
    private CharacterSpy collisionCharacter;
    private GameControllerSpy gameController;

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
            setInitialPosition(STOPPED_DIRECTION, additional);

            testOperation();
        }

        private void testCollisionCharacterMoveUp() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.UP, additional);

            collisionCharacter.moveUp();
            testOperation();
        }

        private void testCollisionCharacterMoveRight() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.RIGHT, additional);

            collisionCharacter.moveRight();
            testOperation();
        }

        private void testCollisionCharacterMoveDown() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.DOWN, additional);

            collisionCharacter.moveDown();
            testOperation();
        }

        private void testCollisionCharacterMoveLeft() {
            generateNewScenario();
            moveCharacter();
            setInitialPosition(Direction.LEFT, additional);

            collisionCharacter.moveLeft();
            testOperation();
        }

        public abstract void moveCharacter();

        public abstract void testOperation();
    }

    public void setUp() {
        gameController = new GameControllerSpy();
        mapHelper = new MapHelper(gameController);
        map = new MapSpy();
        map.setCanMoveTo(true);
        mapHelper.setMap(map);
        generateNewScenario();
    }

    private void generateNewScenario() {
        characterElements = (
            RandomGenerator.generateRandomCharacterElements()
        );
        mapHelper.setCharacterElements(characterElements);
        generateCharacterTest();
        collisionCharacter = getCollisionCharacter();

        MapController mapController = gameController.getMapController();
        mapController.setPlayerCharacterElement(characterElement);
    }

    private void generateCharacterTest() {
        characterElement = RandomGenerator.getRandomCharacterElement(
            characterElements
        );
        character = (CharacterSpy) characterElement.getCharacter();

        int x = RandomGenerator.getRandomPosition() + MINIMUM_POSITION;
        int y = RandomGenerator.getRandomPosition() + MINIMUM_POSITION;
        character.setInitialPosition(x, y);
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

        Assert.assertFalse(mapHelper.canMove(character));
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
                Assert.assertFalse(mapHelper.canMove(character));
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
                Assert.assertFalse(mapHelper.canMove(character));
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
                Assert.assertFalse(mapHelper.canMove(character));
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
                Assert.assertFalse(mapHelper.canMove(character));
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
                Assert.assertTrue(mapHelper.canMove(character));
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
                Assert.assertTrue(mapHelper.canMove(character));
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
                Assert.assertTrue(mapHelper.canMove(character));
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
                Assert.assertTrue(mapHelper.canMove(character));
            }
        };
        test.test();
    }

    public void testGetInteractActionWhenAnCharacterIsInFrontUpLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkGetInteractActionWhenAnCharacterIsInFrontUp();
        }
    }

    private void checkGetInteractActionWhenAnCharacterIsInFrontUp() {
        generateNewScenario();
        character.moveUp();

        checkGetInteractActionWhenAnCharacterIsInFront();
    }

    public void testGetInteractActionWhenAnCharacterIsInFrontRightLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkGetInteractActionWhenAnCharacterIsInFrontRight();
        }
    }

    private void checkGetInteractActionWhenAnCharacterIsInFrontRight() {
        generateNewScenario();
        character.moveRight();

        checkGetInteractActionWhenAnCharacterIsInFront();
    }

    public void testGetInteractActionWhenAnCharacterIsInFrontDownLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkGetInteractActionWhenAnCharacterIsInFrontDown();
        }
    }

    private void checkGetInteractActionWhenAnCharacterIsInFrontDown() {
        generateNewScenario();
        character.moveDown();

        checkGetInteractActionWhenAnCharacterIsInFront();
    }

    public void testGetInteractActionWhenAnCharacterIsInFrontLeftLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkGetInteractActionWhenAnCharacterIsInFrontLeft();
        }
    }

    private void checkGetInteractActionWhenAnCharacterIsInFrontLeft() {
        generateNewScenario();
        character.moveLeft();

        checkGetInteractActionWhenAnCharacterIsInFront();
    }

    private void checkGetInteractActionWhenAnCharacterIsInFront() {
        character.cancelMove();
        setInitialPosition(STOPPED_DIRECTION, 1);

        mapHelper.executeInteractAction();
        CharacterActionSpy interactAction = (
            (CharacterActionSpy) gameController.getExecutedAction()
        );
        Assert.assertSame(collisionCharacter, interactAction.getCharacter());
    }

    private int getAdditionalXValue(final int additional) {
        return (additional * getDirectionAdditionalX(character.getDirection()));
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

    private int getAdditionalYValue(final int additional) {
        return (additional * getDirectionAdditionalY(character.getDirection()));
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

    public void testGetNullActionWhenAnyCharacterIsInFrontUp() {
        generateNewScenario();
        character.moveUp();

        checkGetNullActionWhenAnyCharacterIsInFront();
    }

    public void testGetNullActionWhenAnyCharacterIsInFrontRight() {
        generateNewScenario();
        character.moveRight();

        checkGetNullActionWhenAnyCharacterIsInFront();
    }

    public void testGetNullActionWhenAnyCharacterIsInFrontDown() {
        generateNewScenario();
        character.moveDown();

        checkGetNullActionWhenAnyCharacterIsInFront();
    }

    public void testGetNullActionWhenAnyCharacterIsInFrontLeft() {
        generateNewScenario();
        character.moveLeft();

        checkGetNullActionWhenAnyCharacterIsInFront();
    }

    private void checkGetNullActionWhenAnyCharacterIsInFront() {
        character.cancelMove();
        setInitialPosition(STOPPED_DIRECTION, 2);

        mapHelper.executeInteractAction();
        Action action = gameController.getExecutedAction();

        Assert.assertNotNull(action);
        Assert.assertTrue(action instanceof NullAction);
    }

    private void setInitialPosition(
        final byte direction,
        final int additional
    ) {
        int additionalXValue = getAdditionalXValue(additional);
        int additionalXCollisionCharacterValue = (
            getDirectionAdditionalX(direction)
        );
        int additionalYValue = getAdditionalYValue(additional);
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
}

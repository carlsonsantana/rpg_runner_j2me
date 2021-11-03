package org.rpgrunner.helper;

import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.event.ActionQueueSpy;
import org.rpgrunner.test.mock.event.action.CharacterActionSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class MapHelperTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MINIMUM_POSITION = 3;
    private MapHelper mapHelper;
    private ActionQueueSpy actionQueue;
    private MapSpy map;
    private Vector characterElements;
    private CharacterElement characterElement;
    private CharacterSpy character;
    private CharacterSpy collisionCharacter;

    public void setUp() {
        actionQueue = new ActionQueueSpy();
        mapHelper = new MapHelper(actionQueue);
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

    public void testCantMoveWhenExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            byte direction = RandomGenerator.getRandomDirection();
            checkCantMoveWhenExistsACharacterCollision(direction);
        }
    }

    private void checkCantMoveWhenExistsACharacterCollision(
        final byte direction
    ) {
        checkCollisionCharacterCantMoveTo(direction, Direction.NO_DIRECTION);
        checkCollisionCharacterCantMoveTo(direction, Direction.UP);
        checkCollisionCharacterCantMoveTo(direction, Direction.RIGHT);
        checkCollisionCharacterCantMoveTo(direction, Direction.DOWN);
        checkCollisionCharacterCantMoveTo(direction, Direction.LEFT);
    }

    private void checkCollisionCharacterCantMoveTo(
        final byte direction,
        final byte collisionDirection
    ) {
        createCollisionScenario(direction, collisionDirection, 1);
        Assert.assertFalse(mapHelper.canMove(character));
    }

    public void testCanMoveWhenNotExistsACharacterCollisionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            byte direction = RandomGenerator.getRandomDirection();
            checkCanMoveWhenNotExistsACharacterCollision(direction);
        }
    }

    private void checkCanMoveWhenNotExistsACharacterCollision(
        final byte direction
    ) {
        checkCollisionCharacterCanMoveTo(direction, Direction.NO_DIRECTION);
        checkCollisionCharacterCanMoveTo(direction, Direction.UP);
        checkCollisionCharacterCanMoveTo(direction, Direction.RIGHT);
        checkCollisionCharacterCanMoveTo(direction, Direction.DOWN);
        checkCollisionCharacterCanMoveTo(direction, Direction.LEFT);
    }

    private void checkCollisionCharacterCanMoveTo(
        final byte direction,
        final byte collisionDirection
    ) {
        createCollisionScenario(direction, collisionDirection, 2);
        Assert.assertTrue(mapHelper.canMove(character));
    }

    private void createCollisionScenario(
        final byte direction,
        final byte collisionDirection,
        final int additional
    ) {
        generateNewScenario();
        character.moveTo(direction);
        setInitialPosition(collisionDirection, additional);

        collisionCharacter.moveTo(collisionDirection);
    }

    public void testGetInteractActionWhenAnCharacterIsInFrontLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            byte direction = RandomGenerator.getRandomDirection();
            checkGetInteractActionWhenAnCharacterIsInFrontTo(direction);
        }
    }

    private void checkGetInteractActionWhenAnCharacterIsInFrontTo(
        final byte direction
    ) {
        generateNewScenario();
        character.moveTo(direction);

        character.cancelMove();
        setInitialPosition(Direction.NO_DIRECTION, 1);

        mapHelper.executeInteractAction(character);
        CharacterActionSpy interactAction = (
            (CharacterActionSpy) actionQueue.getActions()[0]
        );
        interactAction.execute();

        Assert.assertSame(collisionCharacter, interactAction.getCharacter());

        actionQueue.clear();
    }

    public void testGetNullActionWhenAnyCharacterIsInFront() {
        checkGetNullActionWhenAnyCharacterIsInFront(Direction.UP);
        checkGetNullActionWhenAnyCharacterIsInFront(Direction.RIGHT);
        checkGetNullActionWhenAnyCharacterIsInFront(Direction.DOWN);
        checkGetNullActionWhenAnyCharacterIsInFront(Direction.LEFT);
    }

    private void checkGetNullActionWhenAnyCharacterIsInFront(
        final byte direction
    ) {
        generateNewScenario();
        character.moveTo(direction);
        character.cancelMove();
        setInitialPosition(Direction.NO_DIRECTION, 2);

        mapHelper.executeInteractAction(character);
        Action action = actionQueue.getActions()[0];

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
}

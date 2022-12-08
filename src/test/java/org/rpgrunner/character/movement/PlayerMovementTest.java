package org.rpgrunner.character.movement;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.helper.InputSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class PlayerMovementTest extends TestCase implements MovementTest {
    private static final boolean[] ONLY_HOLDING_UP = new boolean[] {
        true,
        false,
        false,
        false
    };
    private static final boolean[] ONLY_HOLDING_RIGHT = new boolean[] {
        false,
        true,
        false,
        false
    };
    private static final boolean[] ONLY_HOLDING_DOWN = new boolean[] {
        false,
        false,
        true,
        false
    };
    private static final boolean[] ONLY_HOLDING_LEFT = new boolean[] {
        false,
        false,
        false,
        true
    };
    private static final int UP_INDEX = 0;
    private static final int RIGHT_INDEX = 1;
    private static final int DOWN_INDEX = 2;
    private static final int LEFT_INDEX = 3;
    private CharacterSpy character;
    private MapHelperSpy mapHelper;
    private InputSpy input;

    public void setUp() {
        character = new CharacterSpy();

        mapHelper = new MapHelperSpy();
        input = new InputSpy();
        input.setHoldingUp(false);
        input.setHoldingRight(false);
        input.setHoldingDown(false);
        input.setHoldingLeft(false);
        input.setActionPressed(false);
    }

    public void testExecuteWithoutPressedButton() {
        PlayerMovement playerMovement = createPlayerMovement();
        byte direction = character.getDirection();

        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    public void testMoveUp() {
        checkMove(Direction.UP, ONLY_HOLDING_UP);
    }

    public void testMoveRight() {
        checkMove(Direction.RIGHT, ONLY_HOLDING_RIGHT);
    }

    public void testMoveDown() {
        checkMove(Direction.DOWN, ONLY_HOLDING_DOWN);
    }

    public void testMoveLeft() {
        checkMove(Direction.LEFT, ONLY_HOLDING_LEFT);
    }

    private void checkMove(
        final byte direction,
        final boolean[] holdingPositions
    ) {
        PlayerMovement playerMovement = createPlayerMovement();
        setHolding(holdingPositions);
        Assert.assertFalse(character.isStartAnimationCalled());
        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
        Assert.assertTrue(character.isStartAnimationCalled());
    }

    public void testInteract() {
        PlayerMovement playerMovement = createPlayerMovement();
        input.setActionPressed(true);

        playerMovement.execute();

        Assert.assertTrue(mapHelper.isExecuteInteractActionCalled());
    }

    public void testDoNotMoveWhenCharacterIsMoving() {
        checkDoNotMoveWhenCharacterIsMoving(Direction.UP, ONLY_HOLDING_UP);
        checkDoNotMoveWhenCharacterIsMoving(
            Direction.RIGHT,
            ONLY_HOLDING_RIGHT
        );
        checkDoNotMoveWhenCharacterIsMoving(Direction.DOWN, ONLY_HOLDING_DOWN);
        checkDoNotMoveWhenCharacterIsMoving(Direction.LEFT, ONLY_HOLDING_LEFT);
    }

    private void checkDoNotMoveWhenCharacterIsMoving(
        final byte direction,
        final boolean[] holdingPositions
    ) {
        character.cancelMove();

        if (direction == Direction.DOWN) {
            character.moveLeft();
        } else {
            character.moveDown();
        }

        PlayerMovement playerMovement = createPlayerMovement();

        setHolding(holdingPositions);
        playerMovement.execute();

        Assert.assertFalse(direction == character.getDirection());
    }

    public void testCancelMoveWhenCharacterCantMove() {
        mapHelper.setCanMove(false);

        checkCancelMoveWhenCharacterCantMove(Direction.UP, ONLY_HOLDING_UP);
        checkCancelMoveWhenCharacterCantMove(
            Direction.RIGHT,
            ONLY_HOLDING_RIGHT
        );
        checkCancelMoveWhenCharacterCantMove(Direction.DOWN, ONLY_HOLDING_DOWN);
        checkCancelMoveWhenCharacterCantMove(Direction.LEFT, ONLY_HOLDING_LEFT);
    }

    private void checkCancelMoveWhenCharacterCantMove(
        final byte direction,
        final boolean[] holdingPositions
    ) {
        PlayerMovement playerMovement = createPlayerMovement();
        setHolding(holdingPositions);
        playerMovement.execute();

        Assert.assertFalse(character.isMoving());
    }

    private void setHolding(final boolean[] holdingPositions) {
        input.setHoldingUp(holdingPositions[UP_INDEX]);
        input.setHoldingRight(holdingPositions[RIGHT_INDEX]);
        input.setHoldingDown(holdingPositions[DOWN_INDEX]);
        input.setHoldingLeft(holdingPositions[LEFT_INDEX]);
    }

    private PlayerMovement createPlayerMovement() {
        mapHelper.resetExecuteInteractActionCalled();

        return new PlayerMovement(character, mapHelper, input);
    }
}

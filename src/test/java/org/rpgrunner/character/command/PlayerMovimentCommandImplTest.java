package org.rpgrunner.character.command;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.Direction;
import org.rpgrunner.j2me.character.command.PlayerMovimentCommandImpl;
import org.rpgrunner.test.mock.SimpleCharacter;

public class PlayerMovimentCommandImplTest extends TestCase {
    public void testExecuteWithoutPressedButton() {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovimentCommand playerMovimentCommand = (
            new PlayerMovimentCommandImpl(character)
        );
        byte direction = character.getDirection();

        playerMovimentCommand.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    public void testMoveUp() {
        testMove(Direction.UP, GameCanvas.UP, GameCanvas.KEY_NUM2);
    }

    public void testMoveRight() {
        testMove(Direction.RIGHT, GameCanvas.RIGHT, GameCanvas.KEY_NUM6);
    }

    public void testMoveDown() {
        testMove(Direction.DOWN, GameCanvas.DOWN, GameCanvas.KEY_NUM8);
    }

    public void testMoveLeft() {
        testMove(Direction.LEFT, GameCanvas.LEFT, GameCanvas.KEY_NUM4);
    }

    private void testMove(
        final byte direction,
        final int directDirection,
        final int numberDirection
    ) {
        testMove(direction, directDirection);
        testMove(direction, numberDirection);
    }

    private void testMove(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovimentCommand playerMovimentCommand = (
            new PlayerMovimentCommandImpl(character)
        );

        playerMovimentCommand.pressKey(keyDirection);
        playerMovimentCommand.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    public void testMoveUpReleaseKey() {
        testReleaseKey(Direction.UP, GameCanvas.UP, GameCanvas.KEY_NUM2);
    }

    public void testMoveRightReleaseKey() {
        testReleaseKey(Direction.RIGHT, GameCanvas.RIGHT, GameCanvas.KEY_NUM6);
    }

    public void testMoveDownReleaseKey() {
        testReleaseKey(Direction.DOWN, GameCanvas.DOWN, GameCanvas.KEY_NUM8);
    }

    public void testMoveLeftReleaseKey() {
        testReleaseKey(Direction.LEFT, GameCanvas.LEFT, GameCanvas.KEY_NUM4);
    }

    private void testReleaseKey(
        final byte direction,
        final int directDirection,
        final int numberDirection
    ) {
        testReleaseKeyFirst(direction, directDirection);
        testReleaseKeyFirst(direction, numberDirection);
        testReleaseKeyLast(direction, directDirection);
        testReleaseKeyLast(direction, numberDirection);
    }

    private void testReleaseKeyFirst(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovimentCommand playerMovimentCommand = (
            new PlayerMovimentCommandImpl(character)
        );

        byte reverseDirection = Direction.invertDirection(direction);
        int keyReverseDirection = getKeyDirection(reverseDirection);
        playerMovimentCommand.pressKey(keyReverseDirection);
        playerMovimentCommand.pressKey(keyDirection);
        playerMovimentCommand.releaseKey(keyReverseDirection);
        playerMovimentCommand.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    private void testReleaseKeyLast(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovimentCommand playerMovimentCommand = (
            new PlayerMovimentCommandImpl(character)
        );

        byte reverseDirection = Direction.invertDirection(direction);
        int keyReverseDirection = getKeyDirection(reverseDirection);
        playerMovimentCommand.pressKey(keyDirection);
        playerMovimentCommand.pressKey(keyReverseDirection);
        playerMovimentCommand.releaseKey(keyReverseDirection);
        playerMovimentCommand.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    private int getKeyDirection(byte direction) {
        if (Direction.isUp(direction)) {
            return GameCanvas.UP;
        } else if (Direction.isRight(direction)) {
            return GameCanvas.RIGHT;
        } else if (Direction.isRight(direction)) {
            return GameCanvas.DOWN;
        } else {
            return GameCanvas.LEFT;
        }
    }
}

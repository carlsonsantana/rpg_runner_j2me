package org.rpgrunner.command;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.Direction;
import org.rpgrunner.j2me.command.PlayerCommandImpl;
import org.rpgrunner.test.mock.SimpleCharacter;

public class PlayerCharacterImplTest extends TestCase {
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
        PlayerCommand playerCommand = new PlayerCommandImpl(character);

        playerCommand.pressKey(keyDirection);
        playerCommand.execute();

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
        PlayerCommand playerCommand = new PlayerCommandImpl(character);

        byte reverseDirection = Direction.invertDirection(direction);
        int keyReverseDirection = getKeyDirection(reverseDirection);
        playerCommand.pressKey(keyReverseDirection);
        playerCommand.pressKey(keyDirection);
        playerCommand.releaseKey(keyReverseDirection);
        playerCommand.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    private void testReleaseKeyLast(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerCommand playerCommand = new PlayerCommandImpl(character);

        byte reverseDirection = Direction.invertDirection(direction);
        int keyReverseDirection = getKeyDirection(reverseDirection);
        playerCommand.pressKey(keyDirection);
        playerCommand.pressKey(keyReverseDirection);
        playerCommand.releaseKey(keyReverseDirection);
        playerCommand.execute();

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

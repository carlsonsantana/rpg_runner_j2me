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
}

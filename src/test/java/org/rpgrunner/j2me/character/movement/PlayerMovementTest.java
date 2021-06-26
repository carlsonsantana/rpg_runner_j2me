package org.rpgrunner.j2me.character.movement;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.test.helper.KeyHelper;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;

public abstract class PlayerMovementTest extends TestCase {
    public void testExecuteWithoutPressedButton() {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovement playerMovement = create(character);
        byte direction = character.getDirection();

        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    public void testMoveUp() {
        testMove(Direction.UP, KeyHelper.UP_KEYS);
    }

    public void testMoveRight() {
        testMove(Direction.RIGHT, KeyHelper.RIGHT_KEYS);
    }

    public void testMoveDown() {
        testMove(Direction.DOWN, KeyHelper.DOWN_KEYS);
    }

    public void testMoveLeft() {
        testMove(Direction.LEFT, KeyHelper.LEFT_KEYS);
    }

    private void testMove(final byte direction, final int[] keys) {
        for (int i = 0, length = keys.length; i < length; i++) {
            int key = keys[i];

            testMove(direction, key);
        }
    }

    private void testMove(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovement playerMovement = create(character);

        playerMovement.pressKey(keyDirection);
        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    public void testMoveUpReleaseKey() {
        testReleaseKey(Direction.UP, KeyHelper.UP_KEYS);
    }

    public void testMoveRightReleaseKey() {
        testReleaseKey(Direction.RIGHT, KeyHelper.RIGHT_KEYS);
    }

    public void testMoveDownReleaseKey() {
        testReleaseKey(Direction.DOWN, KeyHelper.DOWN_KEYS);
    }

    public void testMoveLeftReleaseKey() {
        testReleaseKey(Direction.LEFT, KeyHelper.LEFT_KEYS);
    }

    private void testReleaseKey(final byte direction, final int[] keys) {
        for (int i = 0, length = keys.length; i < length; i++) {
            int key = keys[i];

            testReleaseKeyFirst(direction, key);
            testReleaseKeyLast(direction, key);
        }
    }

    private void testReleaseKeyFirst(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovement playerMovement = create(character);

        byte reverseDirection = Direction.invertDirection(direction);
        int keyReverseDirection = getKeyDirection(reverseDirection);
        playerMovement.pressKey(keyReverseDirection);
        playerMovement.pressKey(keyDirection);
        playerMovement.releaseKey(keyReverseDirection);
        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    private void testReleaseKeyLast(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovement playerMovement = create(character);

        byte reverseDirection = Direction.invertDirection(direction);
        int keyReverseDirection = getKeyDirection(reverseDirection);
        playerMovement.pressKey(keyDirection);
        playerMovement.pressKey(keyReverseDirection);
        playerMovement.releaseKey(keyReverseDirection);
        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    private int getKeyDirection(final byte direction) {
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

    public void testInteract() {
        for (
            int i = 0, length = KeyHelper.ACTION_KEYS.length;
            i < length;
            i++
        ) {
            int key = KeyHelper.ACTION_KEYS[i];

            checkInteract(key);
        }
    }

    private void checkInteract(final int key) {
        CharacterSpy character = new CharacterSpy(null);
        PlayerMovement playerMovement = create(character);
        playerMovement.pressKey(key);
        playerMovement.execute();

        Assert.assertFalse(character.isInteractCalled());

        playerMovement.releaseKey(key);
        playerMovement.execute();
        Assert.assertTrue(character.isInteractCalled());
    }

    public void testDoNotInteractTwiceForSamePressedKey() {
        for (
            int i = 0, length = KeyHelper.ACTION_KEYS.length;
            i < length;
            i++
        ) {
            int key = KeyHelper.ACTION_KEYS[i];

            checkDoNotInteractTwiceForSamePressedKey(key);
        }
    }

    private void checkDoNotInteractTwiceForSamePressedKey(final int key) {
        CharacterSpy character = new CharacterSpy(null);
        PlayerMovement playerMovement = create(character);
        playerMovement.pressKey(key);
        playerMovement.releaseKey(key);
        playerMovement.execute();

        character.resetInteractCalled();
        playerMovement.execute();

        Assert.assertFalse(character.isInteractCalled());
    }

    protected abstract PlayerMovement create(GameCharacter character);
}

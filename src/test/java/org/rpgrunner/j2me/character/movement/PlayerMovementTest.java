package org.rpgrunner.j2me.character.movement;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementTest;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.test.helper.KeyHelper;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public abstract class PlayerMovementTest extends TestCase implements
    MovementTest {
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

        int reverseDirectionKey = getReverseDirectionKey(direction);
        playerMovement.pressKey(reverseDirectionKey);
        playerMovement.pressKey(keyDirection);
        playerMovement.releaseKey(reverseDirectionKey);
        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
    }

    private void testReleaseKeyLast(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovement playerMovement = create(character);

        int reverseDirectionKey = getReverseDirectionKey(direction);
        playerMovement.pressKey(keyDirection);
        playerMovement.pressKey(reverseDirectionKey);
        playerMovement.releaseKey(reverseDirectionKey);
        playerMovement.execute();

        Assert.assertEquals(direction, character.getDirection());
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
        MapHelperSpy mapHelper = getMapHelper();
        CharacterSpy character = new CharacterSpy(null);
        PlayerMovement playerMovement = create(character);

        playerMovement.pressKey(key);
        playerMovement.execute();

        Assert.assertFalse(mapHelper.isExecuteInteractActionCalled());

        playerMovement.releaseKey(key);
        playerMovement.execute();
        Assert.assertTrue(mapHelper.isExecuteInteractActionCalled());
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
        MapHelperSpy mapHelper = getMapHelper();
        CharacterSpy character = new CharacterSpy(null);
        PlayerMovement playerMovement = create(character);

        playerMovement.pressKey(key);
        playerMovement.releaseKey(key);
        playerMovement.execute();

        mapHelper.resetExecuteInteractActionCalled();
        playerMovement.execute();

        Assert.assertFalse(mapHelper.isExecuteInteractActionCalled());
    }

    public void testDoNothingWhenReleaseAllKeys() {
        for (
            int i = 0, length = KeyHelper.ACTION_KEYS.length;
            i < length;
            i++
        ) {
            int key = KeyHelper.ACTION_KEYS[i];

            checkDontInteractWhenReleaseAllKeys(key);
        }
    }

    private void checkDontInteractWhenReleaseAllKeys(final int key) {
        MapHelperSpy mapHelper = getMapHelper();
        CharacterSpy character = new CharacterSpy(null);
        PlayerMovement playerMovement = create(character);

        playerMovement.pressKey(key);
        playerMovement.releaseAllKeys();
        playerMovement.releaseKey(key);
        playerMovement.execute();

        Assert.assertFalse(mapHelper.isExecuteInteractActionCalled());
    }

    public void testDontMoveUpWhenReleaseAllKeys() {
        checkDontMoveWhenReleaseAllKeys(Direction.UP, KeyHelper.UP_KEYS);
    }

    public void testDontMoveRightWhenReleaseAllKeys() {
        checkDontMoveWhenReleaseAllKeys(Direction.RIGHT, KeyHelper.RIGHT_KEYS);
    }

    public void testDontMoveDownWhenReleaseAllKeys() {
        checkDontMoveWhenReleaseAllKeys(Direction.DOWN, KeyHelper.DOWN_KEYS);
    }

    public void testDontMoveLeftWhenReleaseAllKeys() {
        checkDontMoveWhenReleaseAllKeys(Direction.LEFT, KeyHelper.LEFT_KEYS);
    }

    private void checkDontMoveWhenReleaseAllKeys(
        final byte direction,
        final int[] keys
    ) {
        for (int i = 0, length = keys.length; i < length; i++) {
            int key = keys[i];

            checkDontMoveWhenReleaseAllKeys(direction, key);
        }
    }

    private void checkDontMoveWhenReleaseAllKeys(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        PlayerMovement playerMovement = create(character);

        int reverseDirectionKey = getReverseDirectionKey(direction);

        playerMovement.pressKey(reverseDirectionKey);
        playerMovement.execute();
        playerMovement.pressKey(keyDirection);
        playerMovement.releaseAllKeys();
        playerMovement.execute();

        Assert.assertFalse(direction == character.getDirection());
    }

    private int getReverseDirectionKey(final byte direction) {
        byte reverseDirection = Direction.invertDirection(direction);

        if (Direction.isUp(reverseDirection)) {
            return GameCanvas.UP;
        } else if (Direction.isRight(reverseDirection)) {
            return GameCanvas.RIGHT;
        } else if (Direction.isDown(reverseDirection)) {
            return GameCanvas.DOWN;
        } else {
            return GameCanvas.LEFT;
        }
    }

    public void testDoNotMoveWhenCharacterIsMoving() {
        checkDoNotMoveWhenCharacterIsMoving(Direction.UP, KeyHelper.UP_KEYS);
        checkDoNotMoveWhenCharacterIsMoving(
            Direction.RIGHT,
            KeyHelper.RIGHT_KEYS
        );
        checkDoNotMoveWhenCharacterIsMoving(
            Direction.DOWN,
            KeyHelper.DOWN_KEYS
        );
        checkDoNotMoveWhenCharacterIsMoving(
            Direction.LEFT,
            KeyHelper.LEFT_KEYS
        );
    }

    private void checkDoNotMoveWhenCharacterIsMoving(
        final byte direction,
        final int[] keys
    ) {
        for (int i = 0, length = keys.length; i < length; i++) {
            int key = keys[i];

            checkDoNotMoveWhenCharacterIsMoving(direction, key);
        }
    }

    private void checkDoNotMoveWhenCharacterIsMoving(
        final byte direction,
        final int keyDirection
    ) {
        SimpleCharacter character = new SimpleCharacter();
        byte initialDirection = character.getDirection();

        if (initialDirection == direction) {
            character.moveLeft();
        }

        PlayerMovement playerMovement = create(character);

        character.setMoving(true);
        playerMovement.pressKey(keyDirection);
        playerMovement.execute();

        Assert.assertFalse(direction == character.getDirection());
    }

    protected abstract PlayerMovement create(GameCharacter character);

    protected abstract MapHelperSpy getMapHelper();
}

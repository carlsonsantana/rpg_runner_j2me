package org.rpgrunner.game;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DirectionTest extends TestCase {
    public void testDetectUpDirection() {
        Assert.assertTrue(Direction.isUp(Direction.UP));
        Assert.assertFalse(Direction.isUp(Direction.RIGHT));
        Assert.assertFalse(Direction.isUp(Direction.DOWN));
        Assert.assertFalse(Direction.isUp(Direction.LEFT));
    }

    public void testDetectRightDirection() {
        Assert.assertFalse(Direction.isRight(Direction.UP));
        Assert.assertTrue(Direction.isRight(Direction.RIGHT));
        Assert.assertFalse(Direction.isRight(Direction.DOWN));
        Assert.assertFalse(Direction.isRight(Direction.LEFT));
    }

    public void testDetectDownDirection() {
        Assert.assertFalse(Direction.isDown(Direction.UP));
        Assert.assertFalse(Direction.isDown(Direction.RIGHT));
        Assert.assertTrue(Direction.isDown(Direction.DOWN));
        Assert.assertFalse(Direction.isDown(Direction.LEFT));
    }

    public void testDetectLeftDirection() {
        Assert.assertFalse(Direction.isLeft(Direction.UP));
        Assert.assertFalse(Direction.isLeft(Direction.RIGHT));
        Assert.assertFalse(Direction.isLeft(Direction.DOWN));
        Assert.assertTrue(Direction.isLeft(Direction.LEFT));
    }
}

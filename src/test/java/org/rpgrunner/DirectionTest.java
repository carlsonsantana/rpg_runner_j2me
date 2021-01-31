package org.rpgrunner;

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

    public void testDetectInvertDirection() {
        byte invertedUp = Direction.invertDirection(Direction.UP);
        byte invertedRight = Direction.invertDirection(Direction.RIGHT);
        byte invertedDown = Direction.invertDirection(Direction.DOWN);
        byte invertedLeft = Direction.invertDirection(Direction.LEFT);

        Assert.assertFalse(Direction.isUp(invertedUp));
        Assert.assertFalse(Direction.isUp(invertedRight));
        Assert.assertTrue(Direction.isUp(invertedDown));
        Assert.assertFalse(Direction.isUp(invertedLeft));

        Assert.assertFalse(Direction.isRight(invertedUp));
        Assert.assertFalse(Direction.isRight(invertedRight));
        Assert.assertFalse(Direction.isRight(invertedDown));
        Assert.assertTrue(Direction.isRight(invertedLeft));

        Assert.assertTrue(Direction.isDown(invertedUp));
        Assert.assertFalse(Direction.isDown(invertedRight));
        Assert.assertFalse(Direction.isDown(invertedDown));
        Assert.assertFalse(Direction.isDown(invertedLeft));

        Assert.assertFalse(Direction.isLeft(invertedUp));
        Assert.assertTrue(Direction.isLeft(invertedRight));
        Assert.assertFalse(Direction.isLeft(invertedDown));
        Assert.assertFalse(Direction.isLeft(invertedLeft));
    }
}

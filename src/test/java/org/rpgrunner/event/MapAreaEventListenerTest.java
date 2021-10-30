package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MapAreaEventListenerTest extends TestCase {
    private final Random random;

    public MapAreaEventListenerTest() {
        random = new Random();
    }

    public void testReturnsSamePositionValues() {
        int x = random.nextInt();
        int y = random.nextInt();
        int width = random.nextInt();
        int height = random.nextInt();

        MapAreaEventListener mapAreaEventListener = new MapAreaEventListener(
            x,
            y,
            width,
            height
        );

        Assert.assertEquals(x, mapAreaEventListener.getX());
        Assert.assertEquals(y, mapAreaEventListener.getY());
        Assert.assertEquals(width, mapAreaEventListener.getWidth());
        Assert.assertEquals(height, mapAreaEventListener.getHeight());
    }
}

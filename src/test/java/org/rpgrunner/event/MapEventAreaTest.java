package org.rpgrunner.event;

import java.util.Random;

import junit.framework.Assert;

import org.rpgrunner.Direction;
import org.rpgrunner.event.action.Action;

public class MapEventAreaTest extends AbstractMapEventTest {
    private final Random random;

    public MapEventAreaTest() {
        random = new Random();
    }

    protected MapEvent createMapEvent(
        final byte directions,
        final Action action
    ) {
        return new MapEventArea(0, 0, 0, 0, directions, action);
    }

    public void testReturnsSamePositionValues() {
        int x = random.nextInt();
        int y = random.nextInt();
        int width = random.nextInt();
        int height = random.nextInt();

        MapEventArea mapEventArea = new MapEventArea(
            x,
            y,
            width,
            height,
            Direction.NO_DIRECTION,
            null
        );

        Assert.assertEquals(x, mapEventArea.getX());
        Assert.assertEquals(y, mapEventArea.getY());
        Assert.assertEquals(width, mapEventArea.getWidth());
        Assert.assertEquals(height, mapEventArea.getHeight());
    }
}

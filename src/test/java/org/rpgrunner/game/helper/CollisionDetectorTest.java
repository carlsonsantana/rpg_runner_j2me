package org.rpgrunner.game.helper;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.MapSpy;

public class CollisionDetectorTest extends TestCase {
    public void testReturnSameMap() {
        CollisionDetector collisionDetector = new CollisionDetector();
        MapSpy map = new MapSpy();
        collisionDetector.setMap(map);

        Assert.assertEquals(map, collisionDetector.getMap());
    }
}

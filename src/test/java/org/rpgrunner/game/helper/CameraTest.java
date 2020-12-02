package org.rpgrunner.game.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CameraTest extends TestCase {
    private Random random;

    public void setUp() {
        random = new Random();
    }

    public void testReturnSameScreenWidthAndHeight() {
        for (int i = 0; i < 100; i++) {
            int randomWidth = random.nextInt(100);
            int randomHeight = random.nextInt(100);
            Camera camera = new Camera(randomWidth, randomHeight);
            Assert.assertEquals(randomWidth, camera.getScreenWidth());
            Assert.assertEquals(randomHeight, camera.getScreenHeight());
        }
    }

    public void testReturnZerosForInitialCameraPositions() {
        int randomWidth = random.nextInt(100);
        int randomHeight = random.nextInt(100);
        Camera camera = new Camera(randomWidth, randomHeight);
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }
}

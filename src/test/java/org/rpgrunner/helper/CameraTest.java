package org.rpgrunner.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CameraTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MAXIMUM_SCREEN_WIDTH = 10;
    private static final int MAXIMUM_SCREEN_HEIGHT = 10;
    private Random random;

    public CameraTest() {
        random = new Random();
    }

    public void testReturnSameScreenWidthAndHeightLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkReturnSameScreenWidthAndHeight();
        }
    }

    private void checkReturnSameScreenWidthAndHeight() {
        int randomWidth = random.nextInt(MAXIMUM_SCREEN_WIDTH);
        int randomHeight = random.nextInt(MAXIMUM_SCREEN_HEIGHT);
        Camera camera = new Camera(randomWidth, randomHeight);
        Assert.assertEquals(randomWidth, camera.getScreenWidth());
        Assert.assertEquals(randomHeight, camera.getScreenHeight());
    }
}

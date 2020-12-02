package org.rpgrunner.game.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CameraTest extends TestCase {
    public void testReturnSameScreenWidth() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int randomWidth = random.nextInt(100);
            Camera camera = new Camera(randomWidth);
            Assert.assertEquals(randomWidth, camera.getScreenWidth());
        }
    }
}

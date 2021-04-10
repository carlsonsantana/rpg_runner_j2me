package org.rpgrunner.controller;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;

public class GameControllerTest extends TestCase {
    private static final int MAXIMUM_KEY_VALUE = 100;
    private final Random random;
    private GameController gameController;
    private MapControllerSpy mapController;
    private GraphicsRenderSpy graphicsRender;

    public GameControllerTest() {
        random = new Random();
    }

    public void setUp() {
        graphicsRender = new GraphicsRenderSpy();
        mapController = new MapControllerSpy();
        gameController = new GameController(graphicsRender, mapController);
    }

    public void testPressKey() {
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, mapController.getPressedKey());
    }

    public void testReleaseKey() {
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, mapController.getReleasedKey());
    }

    public void testPrepareFrameAnimation() {
        gameController.prepareFrameAnimation();

        Assert.assertTrue(mapController.isPrepareFrameAnimationCalled());
    }

    public void testRender() {
        gameController.render();

        Assert.assertTrue(graphicsRender.isRenderCalled());
    }

    public void testSameMessagePassed() {
        String message = RandomGenerator.getRandomString();
        gameController.showMessage(message);

        Assert.assertSame(message, graphicsRender.getLastMessage());
    }
}

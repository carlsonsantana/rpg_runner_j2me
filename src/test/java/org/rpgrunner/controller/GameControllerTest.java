package org.rpgrunner.controller;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;
import org.rpgrunner.test.mock.controller.MessageControllerSpy;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;

public class GameControllerTest extends TestCase {
    private static final int MAXIMUM_KEY_VALUE = 100;
    private final Random random;
    private GameController gameController;
    private MapControllerSpy mapController;
    private MessageControllerSpy messageController;
    private GraphicsRenderSpy graphicsRender;
    private String message;

    public GameControllerTest() {
        random = new Random();
    }

    public void setUp() {
        graphicsRender = new GraphicsRenderSpy();
        mapController = new MapControllerSpy();
        messageController = new MessageControllerSpy();

        gameController = new GameController(
            graphicsRender,
            mapController,
            messageController
        );

        message = RandomGenerator.getRandomString();
    }

    public void testPressKeyMapController() {
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, mapController.getPressedKey());
    }

    public void testReleaseKeyMapController() {
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, mapController.getReleasedKey());
    }

    public void testPressKeyMessageController() {
        gameController.showMessage(message);
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, messageController.getPressedKey());
    }

    public void testReleaseKeyMessageController() {
        gameController.showMessage(message);
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, messageController.getReleasedKey());
    }

    public void testMapControllerPrepareFrameAnimation() {
        Assert.assertFalse(mapController.isPrepareFrameAnimationCalled());

        gameController.prepareFrameAnimation();

        Assert.assertTrue(mapController.isPrepareFrameAnimationCalled());
    }

    public void testMessageControllerPrepareFrameAnimation() {
        gameController.prepareFrameAnimation();
        Assert.assertFalse(messageController.isPrepareFrameAnimationCalled());

        gameController.showMessage(message);

        Assert.assertFalse(messageController.isPrepareFrameAnimationCalled());
        gameController.prepareFrameAnimation();
        Assert.assertTrue(messageController.isPrepareFrameAnimationCalled());
    }

    public void testRender() {
        gameController.render();

        Assert.assertTrue(graphicsRender.isRenderCalled());
    }

    public void testSameMessagePassed() {
        gameController.showMessage(message);

        Assert.assertSame(message, messageController.getLastMessage());
    }

    public void testWhenFinishMessageBackToMapController() {
        gameController.showMessage(message);
        messageController.finish();
        gameController.prepareFrameAnimation();

        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, mapController.getReleasedKey());
    }
}

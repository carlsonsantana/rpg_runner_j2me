package org.rpgrunner.controller;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;
import org.rpgrunner.test.mock.controller.MessageControllerSpy;

public class GameControllerTest extends TestCase {
    private GameController gameController;
    private MapControllerSpy mapController;
    private MessageControllerSpy messageController;
    private String message;

    public void setUp() {
        mapController = new MapControllerSpy();
        messageController = new MessageControllerSpy();

        gameController = new GameController(mapController, messageController);

        message = RandomGenerator.getRandomString();
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

    public void testCallRenderForMapController() {
        Assert.assertFalse(mapController.isRenderCalled());

        gameController.render();

        Assert.assertTrue(mapController.isRenderCalled());
        Assert.assertFalse(messageController.isRenderCalled());
    }

    public void testCallRenderForMessageController() {
        gameController.showMessage(message);
        Assert.assertFalse(messageController.isRenderCalled());

        gameController.render();

        Assert.assertTrue(messageController.isRenderCalled());
        Assert.assertFalse(mapController.isRenderCalled());
    }

    public void testSameMessagePassed() {
        gameController.showMessage(message);

        Assert.assertSame(message, messageController.getLastMessage());
    }

    public void testWhenFinishMessageBackToMapController() {
        gameController.showMessage(message);
        messageController.finish();
        gameController.prepareFrameAnimation();
        gameController.prepareFrameAnimation();

        Assert.assertTrue(mapController.isPrepareFrameAnimationCalled());
    }
}

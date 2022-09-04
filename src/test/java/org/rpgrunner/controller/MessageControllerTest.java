package org.rpgrunner.controller;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.graphics.MessageGraphicsRenderSpy;
import org.rpgrunner.test.mock.helper.InputSpy;

public class MessageControllerTest extends TestCase {
    private MessageController messageController;
    private MessageGraphicsRenderSpy messageGraphicsRender;
    private String message;
    private Random random;
    private InputSpy input;

    public void setUp() {
        message = RandomGenerator.getRandomString();
        messageGraphicsRender = new MessageGraphicsRenderSpy();
        input = new InputSpy();
        messageController = new MessageController(messageGraphicsRender, input);
        random = new Random();
    }

    public void testSameMessagePassed() {
        messageController.showMessage(message);

        Assert.assertTrue(messageGraphicsRender.isShowingMessage());
        Assert.assertSame(message, messageGraphicsRender.getLastMessage());
    }

    public void testHideMessageWhenPressAction() {
        messageController.showMessage(message);
        input.setActionPressed(true);
        messageController.prepareFrameAnimation();

        Assert.assertFalse(messageGraphicsRender.isShowingMessage());
    }

    public void testDoNotHideMessageWhenNotPressAction() {
        messageController.showMessage(message);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isShowingMessage());
    }

    public void testStartMessageControllerFinished() {
        Assert.assertTrue(messageController.isFinished());
    }

    public void testFinishShowedMessageWhenPressAction() {
        messageController.showMessage(message);
        Assert.assertFalse(messageController.isFinished());

        input.setActionPressed(true);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageController.isFinished());
    }

    public void testCallGraphicsRender() {
        Assert.assertFalse(messageGraphicsRender.isRenderCalled());

        messageController.render();

        Assert.assertTrue(messageGraphicsRender.isRenderCalled());
    }

    public void testPageUpWhenPressUp() {
        messageGraphicsRender.clearPage();

        input.setHoldingUp(true);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isPageUpCalled());
    }

    public void testPageDownWhenPressDown() {
        Assert.assertFalse(messageGraphicsRender.isPageDownCalled());

        input.setHoldingDown(true);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isPageDownCalled());
    }

    public void testStopPageUpWhenReleaseUp() {
        input.setHoldingUp(true);
        messageController.prepareFrameAnimation();
        messageGraphicsRender.clearPage();
        input.setHoldingUp(false);
        messageController.prepareFrameAnimation();

        Assert.assertFalse(messageGraphicsRender.isPageUpCalled());
    }

    public void testStopPageDownWhenReleaseDown() {
        input.setHoldingDown(true);
        messageController.prepareFrameAnimation();
        messageGraphicsRender.clearPage();
        input.setHoldingDown(false);
        messageController.prepareFrameAnimation();

        Assert.assertFalse(messageGraphicsRender.isPageDownCalled());
    }
}

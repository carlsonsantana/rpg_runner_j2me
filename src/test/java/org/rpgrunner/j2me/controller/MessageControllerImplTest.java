package org.rpgrunner.j2me.controller;

import java.util.Random;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.graphics.MessageGraphicsRenderSpy;

public class MessageControllerImplTest extends TestCase {
    private MessageControllerImpl messageController;
    private MessageGraphicsRenderSpy messageGraphicsRender;
    private String message;

    public void setUp() {
        message = RandomGenerator.getRandomString();
        messageGraphicsRender = new MessageGraphicsRenderSpy();
        messageController = new MessageControllerImpl(messageGraphicsRender);
    }

    public void testSameMessagePassed() {
        messageController.showMessage(message);

        Assert.assertTrue(messageGraphicsRender.isShowingMessage());
        Assert.assertSame(message, messageGraphicsRender.getLastMessage());
    }

    public void testHideMessageWhenPressAction() {
        checkHideMessageWhenPressAction(GameCanvas.FIRE);
        checkHideMessageWhenPressAction(GameCanvas.KEY_NUM5);
    }

    private void checkHideMessageWhenPressAction(final int key) {
        messageController.showMessage(message);
        messageController.pressKey(key);
        messageController.releaseKey(key);

        Assert.assertFalse(messageGraphicsRender.isShowingMessage());
    }

    public void testDoNotHideMessageWhenNotPressAction() {
        Random random = new Random();
        int key = random.nextInt(Integer.MAX_VALUE);

        if (
            (key == GameCanvas.FIRE)
            || (key == GameCanvas.KEY_NUM5)
        ) {
            key++;
        }

        messageController.showMessage(message);
        messageController.pressKey(key);
        messageController.releaseKey(key);

        Assert.assertTrue(messageGraphicsRender.isShowingMessage());
    }

    public void testStartMessageControllerFinished() {
        Assert.assertTrue(messageController.isFinished());
    }

    public void testFinishShowedMessageWhenPressAction() {
        checkFinishShowedMessageWhenPressAction(GameCanvas.FIRE);
        checkFinishShowedMessageWhenPressAction(GameCanvas.KEY_NUM5);
    }

    private void checkFinishShowedMessageWhenPressAction(final int key) {
        messageController.showMessage(message);
        Assert.assertFalse(messageController.isFinished());

        messageController.pressKey(key);
        messageController.releaseKey(key);

        Assert.assertTrue(messageController.isFinished());
    }

    public void testCallGraphicsRender() {
        Assert.assertFalse(messageGraphicsRender.isRenderCalled());

        messageController.render();

        Assert.assertTrue(messageGraphicsRender.isRenderCalled());
    }

    public void testScrollUpWhenPressUp() {
        Assert.assertFalse(messageGraphicsRender.isScrollUpCalled());

        checkScrollUpWhenPressUp(GameCanvas.UP);
        checkScrollUpWhenPressUp(GameCanvas.KEY_NUM2);
    }

    private void checkScrollUpWhenPressUp(final int key) {
        messageGraphicsRender.clearScroll();

        messageController.pressKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isScrollUpCalled());
    }

    public void testScrollDownWhenPressDown() {
        Assert.assertFalse(messageGraphicsRender.isScrollDownCalled());

        checkScrollDownWhenPressDown(GameCanvas.DOWN);
        checkScrollDownWhenPressDown(GameCanvas.KEY_NUM8);
    }

    private void checkScrollDownWhenPressDown(final int key) {
        messageGraphicsRender.clearScroll();

        messageController.pressKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isScrollDownCalled());
    }
}

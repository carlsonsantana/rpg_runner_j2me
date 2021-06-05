package org.rpgrunner.j2me.controller;

import java.util.Random;

import javax.microedition.lcdui.game.GameCanvas;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;

public class MessageControllerImplTest extends TestCase {
    private MessageControllerImpl messageController;
    private GraphicsRenderSpy graphicsRender;
    private String message;

    public void setUp() {
        message = RandomGenerator.getRandomString();
        graphicsRender = new GraphicsRenderSpy();
        messageController = new MessageControllerImpl(graphicsRender);
    }

    public void testSameMessagePassed() {
        messageController.showMessage(message);

        Assert.assertTrue(graphicsRender.isShowingMessage());
        Assert.assertSame(message, graphicsRender.getLastMessage());
    }

    public void testHideMessageWhenPressAction() {
        checkHideMessageWhenPressAction(GameCanvas.FIRE);
        checkHideMessageWhenPressAction(GameCanvas.KEY_NUM5);
    }

    private void checkHideMessageWhenPressAction(final int key) {
        messageController.showMessage(message);
        messageController.pressKey(key);
        messageController.releaseKey(key);

        Assert.assertFalse(graphicsRender.isShowingMessage());
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

        Assert.assertTrue(graphicsRender.isShowingMessage());
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
}

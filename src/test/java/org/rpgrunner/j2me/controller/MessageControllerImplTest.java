package org.rpgrunner.j2me.controller;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.j2me.Key;
import org.rpgrunner.j2me.helper.InputImpl;
import org.rpgrunner.test.helper.KeyHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.graphics.MessageGraphicsRenderSpy;

public class MessageControllerImplTest extends TestCase {
    private MessageControllerImpl messageController;
    private MessageGraphicsRenderSpy messageGraphicsRender;
    private String message;
    private Random random;
    private InputImpl input;

    public void setUp() {
        message = RandomGenerator.getRandomString();
        messageGraphicsRender = new MessageGraphicsRenderSpy();
        input = new InputImpl();
        messageController = new MessageControllerImpl(
            messageGraphicsRender,
            input
        );
        random = new Random();
    }

    public void testSameMessagePassed() {
        messageController.showMessage(message);

        Assert.assertTrue(messageGraphicsRender.isShowingMessage());
        Assert.assertSame(message, messageGraphicsRender.getLastMessage());
    }

    public void testHideMessageWhenPressAction() {
        for (
            int i = 0, length = KeyHelper.ACTION_KEYS.length;
            i < length;
            i++
        ) {
            int key = KeyHelper.ACTION_KEYS[i];

            checkHideMessageWhenPressAction(key);
        }
    }

    private void checkHideMessageWhenPressAction(final int key) {
        messageController.showMessage(message);
        input.pressKey(key);
        input.releaseKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertFalse(messageGraphicsRender.isShowingMessage());
    }

    public void testDoNotHideMessageWhenNotPressAction() {
        int key = random.nextInt(Integer.MAX_VALUE);

        if (Key.isAction(key)) {
            key++;
        }

        messageController.showMessage(message);
        input.pressKey(key);
        input.pressKey(key);
        input.releaseKey(key);

        Assert.assertTrue(messageGraphicsRender.isShowingMessage());
    }

    public void testStartMessageControllerFinished() {
        Assert.assertTrue(messageController.isFinished());
    }

    public void testFinishShowedMessageWhenPressAction() {
        for (
            int i = 0, length = KeyHelper.ACTION_KEYS.length;
            i < length;
            i++
        ) {
            int key = KeyHelper.ACTION_KEYS[i];

            checkFinishShowedMessageWhenPressAction(key);
        }
    }

    private void checkFinishShowedMessageWhenPressAction(final int key) {
        messageController.showMessage(message);
        Assert.assertFalse(messageController.isFinished());

        input.pressKey(key);
        input.releaseKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageController.isFinished());
    }

    public void testCallGraphicsRender() {
        Assert.assertFalse(messageGraphicsRender.isRenderCalled());

        messageController.render();

        Assert.assertTrue(messageGraphicsRender.isRenderCalled());
    }

    public void testScrollUpWhenPressUp() {
        Assert.assertFalse(messageGraphicsRender.isScrollUpCalled());

        for (int i = 0, length = KeyHelper.UP_KEYS.length; i < length; i++) {
            int key = KeyHelper.UP_KEYS[i];

            checkScrollUpWhenPressUp(key);
        }
    }

    private void checkScrollUpWhenPressUp(final int key) {
        messageGraphicsRender.clearScroll();

        input.pressKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isScrollUpCalled());
    }

    public void testScrollDownWhenPressDown() {
        Assert.assertFalse(messageGraphicsRender.isScrollDownCalled());

        for (int i = 0, length = KeyHelper.DOWN_KEYS.length; i < length; i++) {
            int key = KeyHelper.DOWN_KEYS[i];

            checkScrollDownWhenPressDown(key);
        }
    }

    private void checkScrollDownWhenPressDown(final int key) {
        messageGraphicsRender.clearScroll();

        input.pressKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isScrollDownCalled());
    }

    public void testStopScrollUpWhenReleaseUp() {
        for (int i = 0, length = KeyHelper.UP_KEYS.length; i < length; i++) {
            int key = KeyHelper.UP_KEYS[i];

            checkStopScrollUpWhenReleaseUp(key);
        }
    }

    private void checkStopScrollUpWhenReleaseUp(final int key) {
        input.pressKey(key);
        messageController.prepareFrameAnimation();
        messageGraphicsRender.clearScroll();
        input.releaseKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertFalse(messageGraphicsRender.isScrollUpCalled());
    }

    public void testStopScrollDownWhenReleaseDown() {
        for (int i = 0, length = KeyHelper.DOWN_KEYS.length; i < length; i++) {
            int key = KeyHelper.DOWN_KEYS[i];

            checkStopScrollDownWhenReleaseDown(key);
        }
    }

    private void checkStopScrollDownWhenReleaseDown(final int key) {
        input.pressKey(key);
        messageController.prepareFrameAnimation();
        messageGraphicsRender.clearScroll();
        input.releaseKey(key);
        messageController.prepareFrameAnimation();

        Assert.assertFalse(messageGraphicsRender.isScrollDownCalled());
    }

    public void testDontStopScrollUpWhenReleaseUp() {
        for (int i = 0, length = KeyHelper.UP_KEYS.length; i < length; i++) {
            int key = KeyHelper.UP_KEYS[i];

            checkDontStopScrollUpWhenReleaseUp(key);
        }
    }

    private void checkDontStopScrollUpWhenReleaseUp(final int key) {
        input.pressKey(key);
        messageController.prepareFrameAnimation();
        messageGraphicsRender.clearScroll();
        input.releaseKey(getRandomKeyDifferentOf(key));
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isScrollUpCalled());
    }

    public void testDontStopScrollDownWhenReleaseDown() {
        for (int i = 0, length = KeyHelper.DOWN_KEYS.length; i < length; i++) {
            int key = KeyHelper.DOWN_KEYS[i];

            checkDontStopScrollDownWhenReleaseDown(key);
        }
    }

    private void checkDontStopScrollDownWhenReleaseDown(final int key) {
        input.pressKey(key);
        messageController.prepareFrameAnimation();
        messageGraphicsRender.clearScroll();
        input.releaseKey(getRandomKeyDifferentOf(key));
        messageController.prepareFrameAnimation();

        Assert.assertTrue(messageGraphicsRender.isScrollDownCalled());
    }

    private int getRandomKeyDifferentOf(final int key) {
        int randomKey;

        do {
            randomKey = random.nextInt(Integer.MAX_VALUE);
        } while (randomKey == key);

        return randomKey;
    }
}

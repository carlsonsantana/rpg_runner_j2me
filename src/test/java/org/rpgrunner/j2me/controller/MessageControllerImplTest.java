package org.rpgrunner.j2me.controller;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;

public class MessageControllerImplTest extends TestCase {
    public void testSameMessagePassed() {
        String message = RandomGenerator.getRandomString();
        GraphicsRenderSpy graphicsRender = new GraphicsRenderSpy();
        MessageControllerImpl messageController = new MessageControllerImpl(
            graphicsRender
        );
        messageController.showMessage(message);

        Assert.assertSame(message, graphicsRender.getLastMessage());
    }
}

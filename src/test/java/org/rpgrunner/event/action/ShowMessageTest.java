package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class ShowMessageTest extends TestCase {
    private GameControllerSpy gameController;
    private String message;
    private ShowMessage showMessageAction;

    public void setUp() {
        gameController = new GameControllerSpy();
        message = RandomGenerator.getRandomString();
        showMessageAction = new ShowMessage(gameController, message);
    }

    public void testDontExecuteBeforeCallMethod() {
        Assert.assertNull(gameController.getLastMessage());
    }

    public void testSameMessagePassed() {
        showMessageAction.execute();

        Assert.assertSame(message, gameController.getLastMessage());
    }
}

package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.GameController;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public abstract class AbstractShowMessageTest extends TestCase {
    private GameControllerSpy gameController;
    private String message;
    private ShowMessage showMessageAction;

    public void setUp() {
        gameController = new GameControllerSpy();
        message = RandomGenerator.getRandomString();
        showMessageAction = createShowMessage(gameController, message);
    }

    protected abstract ShowMessage createShowMessage(
        GameController currentGameController,
        String currentMessage
    );

    public void testDontExecuteBeforeCallMethod() {
        Assert.assertNull(gameController.getLastMessage());
    }

    public void testSameMessagePassed() {
        showMessageAction.execute();

        Assert.assertEquals(message, gameController.getLastMessage());
    }
}

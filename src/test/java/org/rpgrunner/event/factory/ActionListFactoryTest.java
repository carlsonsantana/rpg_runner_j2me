package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.event.action.ActionSpy;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class ActionListFactoryTest extends TestCase {
    private static final int MINIMUM_NUMBER_ACTIONS = 2;
    private static final int MAXIMUM_NUMBER_ACTIONS = 100;
    private Random random;
    private ActionAbstractFactorySpy actionAbstractFactory;

    public ActionListFactoryTest() {
        random = new Random();
        actionAbstractFactory = new ActionAbstractFactorySpy();
    }

    public void testActionListFactory() throws IOException {
        int numberActions = (
            random.nextInt(MAXIMUM_NUMBER_ACTIONS)
            + MINIMUM_NUMBER_ACTIONS
        );
        InputStream inputStream = getInputStream(numberActions);

        ActionListFactory actionListFactory = new ActionListFactory(
            actionAbstractFactory
        );
        Action action = actionListFactory.create(inputStream);
        action.execute();

        ActionSpy[] actionsCreated = actionAbstractFactory.getActionsCreated();

        for (int i = 0, length = actionsCreated.length; i < length; i++) {
            ActionSpy actionSpy = actionsCreated[i];
            Assert.assertTrue(actionSpy.isExecuteCalled());
        }
    }

    private InputStream getInputStream(final int numberActions) {
        byte[] byteArray = new byte[1];
        byteArray[0] = (byte) numberActions;

        return new ByteArrayInputStream(byteArray);
    }
}

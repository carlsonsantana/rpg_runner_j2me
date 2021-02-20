package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.AbstractActionListTest;
import org.rpgrunner.event.action.ActionList;
import org.rpgrunner.test.mock.event.action.ActionSpy;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class ActionListFactoryTest extends AbstractActionListTest {
    private ActionAbstractFactorySpy actionAbstractFactory;

    public ActionListFactoryTest() {
        actionAbstractFactory = new ActionAbstractFactorySpy();
    }

    protected ActionList createActionList(final int numberActions) {
        InputStream inputStream = getInputStream(numberActions);
        ActionListFactory actionListFactory = new ActionListFactory(
            actionAbstractFactory
        );

        try {
            ActionList actionList = (
                (ActionList) actionListFactory.create(inputStream)
            );

            return actionList;
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream getInputStream(final int numberActions) {
        byte[] byteArray = new byte[1];
        byteArray[0] = (byte) numberActions;

        return new ByteArrayInputStream(byteArray);
    }

    protected ActionSpy[] getLastActionsCreated() {
        return actionAbstractFactory.getActionsCreated();
    }
}

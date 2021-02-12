package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.ActionList;

public class ActionListFactory implements ActionFactory {
    private final ActionAbstractFactory actionAbstractFactory;

    public ActionListFactory(
        final ActionAbstractFactory newActionAbstractFactory
    ) {
        actionAbstractFactory = newActionAbstractFactory;
    }

    public Action create(final InputStream inputStream) throws IOException {
        int startActionsSize = inputStream.read();
        Action[] actions = new Action[startActionsSize];
        ActionList actionList = new ActionList(actions);

        for (int i = 0; i < startActionsSize; i++) {
            actions[i] = actionAbstractFactory.create(inputStream);
        }

        return actionList;
    }
}

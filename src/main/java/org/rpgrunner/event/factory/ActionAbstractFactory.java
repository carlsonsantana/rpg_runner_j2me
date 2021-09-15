package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;

public class ActionAbstractFactory implements ActionFactory {
    private static final int MAX_ACTION_FACTORIES = 7;
    private final IdentifiedActionFactory[] actionFactories;

    public ActionAbstractFactory() {
        actionFactories = new IdentifiedActionFactory[MAX_ACTION_FACTORIES];
    }

    public void addActionFactory(final IdentifiedActionFactory actionFactory) {
        int index = actionFactory.getId();

        actionFactories[index] = actionFactory;
    }

    public Action create(final InputStream inputStream) throws IOException {
        int actionClass = inputStream.read();
        IdentifiedActionFactory actionFactory = actionFactories[actionClass];

        return actionFactory.create(inputStream);
    }
}

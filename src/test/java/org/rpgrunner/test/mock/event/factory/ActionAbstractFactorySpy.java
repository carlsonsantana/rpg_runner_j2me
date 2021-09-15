package org.rpgrunner.test.mock.event.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.test.mock.controller.MapControllerSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;

public class ActionAbstractFactorySpy extends ActionAbstractFactory {
    private final Vector actionsCreated;

    public ActionAbstractFactorySpy() {
        this(new MapControllerSpy());
    }

    public ActionAbstractFactorySpy(final MapController mapController) {
        actionsCreated = new Vector();
    }

    public Action create(final InputStream inputStream) throws IOException {
        Action action = new ActionSpy();
        actionsCreated.addElement(action);

        return action;
    }

    public ActionSpy[] getActionsCreated() {
        ActionSpy[] arrayActionsCreated = new ActionSpy[actionsCreated.size()];
        int index = 0;

        for (
            Enumeration enumeration = actionsCreated.elements();
            enumeration.hasMoreElements();
        ) {
            arrayActionsCreated[index++] = (
                (ActionSpy) enumeration.nextElement()
            );
        }

        return arrayActionsCreated;
    }
}

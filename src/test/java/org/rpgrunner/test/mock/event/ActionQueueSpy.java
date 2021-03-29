package org.rpgrunner.test.mock.event;

import java.util.Vector;

import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.Action;

public class ActionQueueSpy extends ActionQueue {
    private Vector actions;

    public ActionQueueSpy() {
        actions = new Vector();
    }

    public void push(final Action newAction) {
        actions.addElement(newAction);
    }

    public void execute() {
        while (!actions.isEmpty()) {
            Action action = (Action) actions.firstElement();
            action.execute();

            actions.removeElementAt(0);
        }
    }

    public Action[] getActions() {
        int size = actions.size();
        Action[] arrayActions = new Action[size];

        for (int index = 0; index < size; index++) {
            arrayActions[index] = (Action) actions.elementAt(index);
        }

        return arrayActions;
    }

    public void clear() {
        actions.removeAllElements();
    }
}

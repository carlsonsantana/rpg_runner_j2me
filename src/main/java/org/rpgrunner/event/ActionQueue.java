package org.rpgrunner.event;

import java.util.Vector;

import org.rpgrunner.event.action.Action;

public class ActionQueue {
    private Vector actions;

    public ActionQueue() {
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
}

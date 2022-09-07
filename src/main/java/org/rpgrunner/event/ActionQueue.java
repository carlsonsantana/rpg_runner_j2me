package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;

public class ActionQueue {
    private static final int MAX_ACTIONS = 20;
    private Action[] actions;
    private int index;
    private int size;

    public ActionQueue() {
        actions = new Action[MAX_ACTIONS];
    }

    public void push(final Action newAction) {
        int insertIndex = (size + index) % MAX_ACTIONS;
        actions[insertIndex] = newAction;
        size++;
    }

    public void execute() {
        while (size > 0) {
            Action action = actions[index];
            action.execute();
            actions[index] = null;

            size--;
            index = (index + 1) % MAX_ACTIONS;
        }
    }
}

package org.rpgrunner.event.action;

public class ActionList implements Action {
    private final Action[] actions;

    public ActionList(final Action[] newActions) {
        actions = newActions;
    }

    public void execute() {
        for (int i = 0, length = actions.length; i < length; i++) {
            Action action = actions[i];
            action.execute();
        }
    }
}

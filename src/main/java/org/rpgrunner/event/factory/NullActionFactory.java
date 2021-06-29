package org.rpgrunner.event.factory;

import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public class NullActionFactory implements ActionFactory {
    public Action create(final InputStream inputStream) {
        return new NullAction();
    }
}

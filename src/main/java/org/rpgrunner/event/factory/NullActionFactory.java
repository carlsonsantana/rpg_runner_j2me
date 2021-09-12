package org.rpgrunner.event.factory;

import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public class NullActionFactory implements IdentifiedActionFactory {
    public static final int ID_VALUE = 0;

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) {
        return new NullAction();
    }
}

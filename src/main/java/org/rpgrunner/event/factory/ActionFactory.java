package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;

public interface ActionFactory {
    Action create(InputStream inputStream) throws IOException;
}

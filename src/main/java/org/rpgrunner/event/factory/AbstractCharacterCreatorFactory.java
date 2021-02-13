package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.Loader;

public abstract class AbstractCharacterCreatorFactory implements ActionFactory {
    public Action create(final InputStream inputStream) throws IOException {
        String fileBaseName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();

        return create(
            fileBaseName,
            mapPositionX,
            mapPositionY
        );
    }

    protected abstract AbstractCharacterCreator create(
        String fileBaseName,
        int mapPositionX,
        int mapPositionY
    );
}

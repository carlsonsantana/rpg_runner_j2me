package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.helper.Loader;

public class CharacterCreatorFactory implements IdentifiedActionFactory {
    private static final int ID_VALUE = 3;
    private final MapController mapController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final ActionAbstractFactory actionAbstractFactory;

    public CharacterCreatorFactory(
        final MapController currentMapController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
        final ActionAbstractFactory currentActionAbstractFactory
    ) {
        mapController = currentMapController;
        characterAnimationFactory = currentCharacterAnimationFactory;
        actionAbstractFactory = currentActionAbstractFactory;
    }

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String fileBaseName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();

        return create(
            fileBaseName,
            mapPositionX,
            mapPositionY,
            inputStream
        );
    }

    private AbstractCharacterCreator create(
        final String fileBaseName,
        final int mapPositionX,
        final int mapPositionY,
        final InputStream inputStream
    ) throws IOException {
        Action action = actionAbstractFactory.create(inputStream);

        CharacterCreator characterCreator = new CharacterCreator(
            mapController,
            characterAnimationFactory,
            fileBaseName,
            mapPositionX,
            mapPositionY,
            action
        );

        return characterCreator;
    }
}

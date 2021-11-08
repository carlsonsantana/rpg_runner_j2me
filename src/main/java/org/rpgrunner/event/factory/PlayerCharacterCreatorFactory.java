package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.helper.Input;
import org.rpgrunner.helper.Loader;

public class PlayerCharacterCreatorFactory implements IdentifiedActionFactory {
    private static final int ID_VALUE = 2;
    private final MapController mapController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final Input input;

    public PlayerCharacterCreatorFactory(
        final MapController currentMapController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
        final Input currentInput
    ) {
        mapController = currentMapController;
        characterAnimationFactory = currentCharacterAnimationFactory;
        input = currentInput;
    }

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String fileBaseName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();

        return create(fileBaseName, mapPositionX, mapPositionY);
    }

    private AbstractCharacterCreator create(
        final String fileBaseName,
        final int mapPositionX,
        final int mapPositionY
    ) {
        PlayerCharacterCreator characterCreator = new PlayerCharacterCreator(
            mapController,
            characterAnimationFactory,
            input,
            fileBaseName,
            mapPositionX,
            mapPositionY
        );

        return characterCreator;
    }
}

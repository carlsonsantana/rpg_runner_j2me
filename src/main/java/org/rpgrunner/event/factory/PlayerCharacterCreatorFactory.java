package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.helper.Input;

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
        byte idSprite = (byte) inputStream.read();
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();

        return create(idSprite, mapPositionX, mapPositionY);
    }

    private AbstractCharacterCreator create(
        final byte idSprite,
        final int mapPositionX,
        final int mapPositionY
    ) {
        PlayerCharacterCreator characterCreator = new PlayerCharacterCreator(
            mapController,
            characterAnimationFactory,
            input,
            idSprite,
            mapPositionX,
            mapPositionY
        );

        return characterCreator;
    }
}

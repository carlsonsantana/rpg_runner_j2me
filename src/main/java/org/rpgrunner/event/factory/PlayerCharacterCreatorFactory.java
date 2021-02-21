package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.helper.Loader;

public class PlayerCharacterCreatorFactory implements ActionFactory {
    private final GameController gameController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final PlayerMovementFactory playerMovementFactory;

    public PlayerCharacterCreatorFactory(
        final GameController currentGameController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
        final PlayerMovementFactory currentPlayerMovementFactory
    ) {
        gameController = currentGameController;
        characterAnimationFactory = currentCharacterAnimationFactory;
        playerMovementFactory = currentPlayerMovementFactory;
    }

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

    private AbstractCharacterCreator create(
        final String fileBaseName,
        final int mapPositionX,
        final int mapPositionY
    ) {
        PlayerCharacterCreator characterCreator = new PlayerCharacterCreator(
            gameController,
            characterAnimationFactory,
            playerMovementFactory,
            fileBaseName,
            mapPositionX,
            mapPositionY
        );

        return characterCreator;
    }
}

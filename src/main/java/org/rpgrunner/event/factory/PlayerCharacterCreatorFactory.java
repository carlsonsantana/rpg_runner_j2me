package org.rpgrunner.event.factory;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.PlayerCharacterCreator;

public class PlayerCharacterCreatorFactory
        extends AbstractCharacterCreatorFactory {
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

    protected AbstractCharacterCreator create(
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

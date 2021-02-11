package org.rpgrunner.event.factory;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.PlayerCharacterCreator;

public class PlayerCharacterCreatorFactory
        extends AbstractCharacterCreatorFactory {
    public PlayerCharacterCreatorFactory(final GameController gameController) {
        super(gameController);
    }

    public AbstractCharacterCreator create(
        final GameController gameController,
        final String fileBaseName,
        final int mapPositionX,
        final int mapPositionY
    ) {
        PlayerCharacterCreator characterCreator = new PlayerCharacterCreator(
            gameController,
            fileBaseName,
            mapPositionX,
            mapPositionY
        );

        return characterCreator;
    }
}

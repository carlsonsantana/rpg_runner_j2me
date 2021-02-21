package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.map.MapLoader;

public class ActionAbstractFactory implements ActionFactory {
    private final ActionFactory[] actionFactories;

    public ActionAbstractFactory(
        final GameController gameController,
        final CharacterAnimationFactory characterAnimationFactory,
        final PlayerMovementFactory playerMovementFactory
    ) {
        MapLoader mapLoader = new MapLoader(this);

        NullActionFactory nullActionFactory = new NullActionFactory();
        ActionListFactory actionListFactory = new ActionListFactory(this);
        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(
                gameController,
                characterAnimationFactory,
                playerMovementFactory
            )
        );
        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(
                gameController,
                characterAnimationFactory,
                this
            )
        );
        TeleportFactory teleportFactory = new TeleportFactory(
            gameController,
            mapLoader
        );
        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();

        actionFactories = new ActionFactory[] {
            nullActionFactory,
            actionListFactory,
            playerCharacterCreatorFactory,
            characterCreatorFactory,
            teleportFactory,
            localTeleportFactory
        };
    }

    public Action create(final InputStream inputStream) throws IOException {
        int actionClass = inputStream.read();
        ActionFactory actionFactory = actionFactories[actionClass];

        return actionFactory.create(inputStream);
    }
}

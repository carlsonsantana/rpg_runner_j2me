package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.map.MapLoader;

public class ActionAbstractFactory implements ActionFactory {
    private final IdentifiedActionFactory[] actionFactories;

    public ActionAbstractFactory(
        final GameController gameController,
        final MapController mapController,
        final CharacterAnimationFactory characterAnimationFactory,
        final PlayerMovementFactory playerMovementFactory,
        final ActionQueue actionQueue
    ) {
        MapLoader mapLoader = new MapLoader(this);

        NullActionFactory nullActionFactory = new NullActionFactory();
        ActionListFactory actionListFactory = new ActionListFactory(this);
        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(
                mapController,
                characterAnimationFactory,
                playerMovementFactory
            )
        );
        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(
                mapController,
                characterAnimationFactory,
                this
            )
        );
        TeleportFactory teleportFactory = new TeleportFactory(
            mapController,
            mapLoader,
            actionQueue
        );
        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();
        ShowMessageFactory showMessageFactory = new ShowMessageFactory(
            gameController
        );

        actionFactories = new IdentifiedActionFactory[] {
            nullActionFactory,
            actionListFactory,
            playerCharacterCreatorFactory,
            characterCreatorFactory,
            teleportFactory,
            localTeleportFactory,
            showMessageFactory
        };
    }

    public Action create(final InputStream inputStream) throws IOException {
        int actionClass = inputStream.read();
        IdentifiedActionFactory actionFactory = actionFactories[actionClass];

        return actionFactory.create(inputStream);
    }
}

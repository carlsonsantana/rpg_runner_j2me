package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.Action;

public class ActionAbstractFactory implements ActionFactory {
    private final ActionFactory[] actionFactories;

    public ActionAbstractFactory(final GameController gameController) {
        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(gameController)
        );
        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(gameController)
        );
        TeleportFactory teleportFactory = new TeleportFactory(gameController);
        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();

        actionFactories = new ActionFactory[] {
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

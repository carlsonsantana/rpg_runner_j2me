package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.event.action.Action;

public class ActionAbstractFactory implements ActionFactory {
    private final ActionFactory[] actionFactories;

    public ActionAbstractFactory(
        final GameController gameController,
        final CharacterAnimationFactory characterAnimationFactory
    ) {
        ActionListFactory actionListFactory = new ActionListFactory(this);
        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(
                gameController,
                characterAnimationFactory
            )
        );
        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(
                gameController,
                characterAnimationFactory
            )
        );
        TeleportFactory teleportFactory = new TeleportFactory(gameController);
        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();

        actionFactories = new ActionFactory[] {
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

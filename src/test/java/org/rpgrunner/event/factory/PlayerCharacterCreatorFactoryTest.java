package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class PlayerCharacterCreatorFactoryTest
    extends AbstractCharacterCreatorFactoryTest {
    protected AbstractCharacterCreatorFactory createFactory(
        final GameControllerSpy gameController
    ) {
        return new PlayerCharacterCreatorFactory(gameController);
    }

    protected CharacterElement getCharacterCreated(
        final GameControllerSpy gameController
    ) {
        return gameController.getPlayerCharacterElement();
    }

    protected boolean instanceOfCharacterCreator(final Action action) {
        return action instanceof PlayerCharacterCreator;
    }
}

package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.test.mock.GameControllerSpy;

public abstract class AbstractPlayerCharacterCreatorFactoryTest
    extends AbstractCharacterCreatorFactoryTest {
    protected CharacterElement getCharacterCreated(
        final GameControllerSpy gameController
    ) {
        return gameController.getPlayerCharacterElement();
    }

    protected boolean instanceOfCharacterCreator(final Action action) {
        return action instanceof PlayerCharacterCreator;
    }
}

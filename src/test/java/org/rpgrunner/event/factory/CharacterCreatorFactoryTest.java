package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class CharacterCreatorFactoryTest
    extends AbstractCharacterCreatorFactoryTest {
    protected AbstractCharacterCreatorFactory createFactory(
        final GameControllerSpy gameController
    ) {
        return new CharacterCreatorFactory(gameController);
    }

    protected CharacterElement getCharacterCreated(
        final GameControllerSpy gameController
    ) {
        return gameController.getLastCharacterElementAdded();
    }

    protected boolean instanceOfCharacterCreator(final Action action) {
        return action instanceof CharacterCreator;
    }
}

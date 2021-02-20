package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.test.mock.GameControllerSpy;

public abstract class AbstractNPCCreatorFactoryTest
    extends AbstractCharacterCreatorFactoryTest {
    protected CharacterElement getCharacterCreated(
        final GameControllerSpy gameController
    ) {
        return gameController.getLastCharacterElementAdded();
    }

    protected boolean instanceOfCharacterCreator(final Action action) {
        return action instanceof CharacterCreator;
    }
}

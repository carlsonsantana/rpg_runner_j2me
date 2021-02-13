package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;

public class CharacterCreatorFactoryTest
        extends AbstractCharacterCreatorFactoryTest {
    protected AbstractCharacterCreatorFactory createFactory(
        final GameControllerSpy gameController
    ) {
        CharacterAnimationFactory characterAnimationFactory = (
            new CharacterAnimationFactoryMock()
        );

        return new CharacterCreatorFactory(
            gameController,
            characterAnimationFactory
        );
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

package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;

public class PlayerCharacterCreatorFactoryTest
        extends AbstractCharacterCreatorFactoryTest {
    protected AbstractCharacterCreatorFactory createFactory(
        final GameControllerSpy gameController
    ) {
        CharacterAnimationFactory characterAnimationFactory = (
            new CharacterAnimationFactoryMock()
        );
        PlayerMovementFactory playerMovementFactory = (
            new PlayerMovementFactoryMock()
        );

        return new PlayerCharacterCreatorFactory(
            gameController,
            characterAnimationFactory,
            playerMovementFactory
        );
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

package org.rpgrunner.event.factory;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.test.mock.GameControllerSpy;

public class PlayerCharacterCreatorFactoryTest
        extends AbstractCharacterCreatorFactoryTest {
    public AbstractCharacterCreatorFactory createFactory(
        final GameControllerSpy gameController
    ) {
        return new PlayerCharacterCreatorFactory(gameController);
    }

    public CharacterElement getCharacterCreated(
        final GameControllerSpy gameController
    ) {
        return gameController.getPlayerCharacterElement();
    }
}

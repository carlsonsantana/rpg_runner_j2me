package org.rpgrunner.event.action;

import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;

public class CharacterCreatorTest extends AbstractCharacterCreatorTest {
    private final CharacterAnimationFactory characterAnimationFactory;

    public CharacterCreatorTest() {
        characterAnimationFactory = new CharacterAnimationFactoryMock();
    }

    protected CharacterCreator createCharacterCreator(
        final GameController gameController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        MapController mapController = gameController.getMapController();
        Action action = new NullAction();

        return new CharacterCreator(
            mapController,
            characterAnimationFactory,
            characterFileName,
            initialMapPositionX,
            initialMapPositionY,
            action
        );
    }
}

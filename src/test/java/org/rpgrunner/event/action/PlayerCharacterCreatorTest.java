package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.helper.InputSpy;

public class PlayerCharacterCreatorTest
    extends AbstractPlayerCharacterCreatorTest {
    private final CharacterAnimationFactory characterAnimationFactory;
    private final InputSpy input;

    public PlayerCharacterCreatorTest() {
        characterAnimationFactory = new CharacterAnimationFactoryMock();
        input = new InputSpy();
    }

    protected PlayerCharacterCreator createPlayerCharacterCreator(
        final MapController mapController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        return new PlayerCharacterCreator(
            mapController,
            characterAnimationFactory,
            input,
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );
    }
}

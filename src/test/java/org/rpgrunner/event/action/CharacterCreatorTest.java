package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.event.CharacterEventListenerSpy;

public class CharacterCreatorTest extends AbstractCharacterCreatorTest {
    private final CharacterAnimationFactory characterAnimationFactory;

    public CharacterCreatorTest() {
        characterAnimationFactory = new CharacterAnimationFactoryMock();
    }

    protected CharacterCreator createCharacterCreator(
        final MapController mapController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        Action action = new NullAction();
        CharacterEventListenerSpy characterEventListenerSpy = (
            new CharacterEventListenerSpy()
        );
        characterEventListenerSpy.setInteractAction(action);

        return new CharacterCreator(
            mapController,
            characterAnimationFactory,
            characterFileName,
            initialMapPositionX,
            initialMapPositionY,
            characterEventListenerSpy
        );
    }
}

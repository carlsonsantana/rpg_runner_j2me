package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.mock.character.CharacterFactoryMock;
import org.rpgrunner.test.mock.event.CharacterEventListenerSpy;

public class CharacterCreatorTest extends AbstractCharacterCreatorTest {
    private final CharacterFactory characterFactory;

    public CharacterCreatorTest() {
        characterFactory = new CharacterFactoryMock();
    }

    protected CharacterCreator createCharacterCreator(
        final MapController mapController,
        final byte characterIDSprite,
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
            characterFactory,
            characterIDSprite,
            initialMapPositionX,
            initialMapPositionY,
            characterEventListenerSpy
        );
    }
}

package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.mock.character.CharacterFactoryMock;
import org.rpgrunner.test.mock.helper.InputSpy;

public class PlayerCharacterCreatorTest
extends AbstractPlayerCharacterCreatorTest {
    private final CharacterFactory characterFactory;
    private final InputSpy input;

    public PlayerCharacterCreatorTest() {
        characterFactory = new CharacterFactoryMock();
        input = new InputSpy();
    }

    protected PlayerCharacterCreator createPlayerCharacterCreator(
        final MapController mapController,
        final byte characterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        return new PlayerCharacterCreator(
            mapController,
            characterFactory,
            input,
            characterIDSprite,
            initialMapPositionX,
            initialMapPositionY
        );
    }
}

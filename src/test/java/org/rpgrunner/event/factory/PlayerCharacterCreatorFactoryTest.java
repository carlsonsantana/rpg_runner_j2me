package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.character.CharacterFactoryMock;
import org.rpgrunner.test.mock.helper.InputSpy;

public class PlayerCharacterCreatorFactoryTest
extends AbstractPlayerCharacterCreatorFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(
        final MapController mapController,
        final InputStream inputStream
    ) throws IOException {
        CharacterFactory characterFactory = new CharacterFactoryMock();
        InputSpy input = new InputSpy();

        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(
                mapController,
                characterFactory,
                input
            )
        );
        Action action = playerCharacterCreatorFactory.create(inputStream);

        return action;
    }
}

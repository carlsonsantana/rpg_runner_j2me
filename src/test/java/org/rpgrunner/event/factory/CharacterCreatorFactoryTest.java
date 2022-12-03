package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.HelperActionAbstractFactory;
import org.rpgrunner.test.mock.character.CharacterFactoryMock;

public class CharacterCreatorFactoryTest
extends AbstractCharacterCreatorFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(
        final MapController mapController,
        final InputStream inputStream
    ) throws IOException {
        ActionAbstractFactory actionAbstractFactory = (
            HelperActionAbstractFactory.createActionAbstractFactory(
                mapController
            )
        );
        CharacterFactory characterFactory = new CharacterFactoryMock();

        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(
                mapController,
                characterFactory,
                actionAbstractFactory
            )
        );
        Action action = characterCreatorFactory.create(inputStream);

        return action;
    }
}

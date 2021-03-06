package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;

public class PlayerCharacterCreatorFactoryTest
    extends AbstractPlayerCharacterCreatorFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(
        final MapController mapController,
        final InputStream inputStream
    ) throws IOException {
        CharacterAnimationFactory characterAnimationFactory = (
            new CharacterAnimationFactoryMock()
        );
        PlayerMovementFactory playerMovementFactory = (
            new PlayerMovementFactoryMock()
        );

        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(
                mapController,
                characterAnimationFactory,
                playerMovementFactory
            )
        );
        Action action = playerCharacterCreatorFactory.create(inputStream);

        return action;
    }
}

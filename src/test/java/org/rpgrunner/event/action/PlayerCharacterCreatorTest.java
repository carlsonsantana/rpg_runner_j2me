package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.MapController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;

public class PlayerCharacterCreatorTest
    extends AbstractPlayerCharacterCreatorTest {
    private final CharacterAnimationFactory characterAnimationFactory;
    private final PlayerMovementFactory playerMovementFactory;

    public PlayerCharacterCreatorTest() {
        characterAnimationFactory = new CharacterAnimationFactoryMock();
        playerMovementFactory = new PlayerMovementFactoryMock();
    }

    protected PlayerCharacterCreator createPlayerCharacterCreator(
        final GameController gameController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        MapController mapController = gameController.getMapController();

        return new PlayerCharacterCreator(
            mapController,
            characterAnimationFactory,
            playerMovementFactory,
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );
    }
}

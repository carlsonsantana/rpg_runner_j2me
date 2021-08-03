package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.controller.MapController;

public class PlayerCharacterCreator extends AbstractCharacterCreator {
    private final MapController mapController;
    private final PlayerMovementFactory playerMovementFactory;

    public PlayerCharacterCreator(
        final MapController currentMapController,
        final CharacterAnimationFactory characterAnimationFactory,
        final PlayerMovementFactory currentPlayerMovementFactory,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        super(
            currentMapController,
            characterAnimationFactory,
            newCharacterFileBaseName,
            initialMapPositionX,
            initialMapPositionY,
            null
        );
        mapController = currentMapController;
        playerMovementFactory = currentPlayerMovementFactory;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character,
        final CharacterAnimation characterAnimation
    ) {
        return playerMovementFactory.createPlayerMovement(
            character,
            characterAnimation
        );
    }

    protected void displayCharacter(final CharacterElement characterElement) {
        mapController.setPlayerCharacterElement(characterElement);
    }
}

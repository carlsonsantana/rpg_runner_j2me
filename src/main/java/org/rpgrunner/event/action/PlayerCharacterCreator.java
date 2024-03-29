package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.helper.Input;
import org.rpgrunner.helper.MapHelper;

public class PlayerCharacterCreator extends AbstractCharacterCreator {
    private final MapController mapController;
    private final Input input;

    public PlayerCharacterCreator(
        final MapController currentMapController,
        final CharacterAnimationFactory characterAnimationFactory,
        final Input currentInput,
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
        input = currentInput;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character,
        final CharacterAnimation characterAnimation
    ) {
        MapHelper mapHelper = mapController.getMapHelper();

        return new PlayerMovement(
            character,
            characterAnimation,
            mapHelper,
            input
        );
    }

    protected void displayCharacter(final CharacterElement characterElement) {
        mapController.setPlayerCharacterElement(characterElement);
    }
}

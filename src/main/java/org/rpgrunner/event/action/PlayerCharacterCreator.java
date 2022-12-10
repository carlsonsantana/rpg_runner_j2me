package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterFactory;
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
        final CharacterFactory characterFactory,
        final Input currentInput,
        final byte newCharacterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        super(
            currentMapController,
            characterFactory,
            newCharacterIDSprite,
            initialMapPositionX,
            initialMapPositionY,
            null
        );
        mapController = currentMapController;
        input = currentInput;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character
    ) {
        MapHelper mapHelper = mapController.getMapHelper();

        return new PlayerMovement(character, mapHelper, input);
    }

    protected void displayCharacter(final GameCharacter character) {
        mapController.setPlayerCharacter(character);
    }
}

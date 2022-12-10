package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.RandomMovement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.helper.MapHelper;

public class CharacterCreator extends AbstractCharacterCreator {
    private final MapController mapController;

    public CharacterCreator(
        final MapController currentMapController,
        final CharacterFactory characterFactory,
        final byte newCharacterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final CharacterEventListener characterEventListener
    ) {
        super(
            currentMapController,
            characterFactory,
            newCharacterIDSprite,
            initialMapPositionX,
            initialMapPositionY,
            characterEventListener
        );
        mapController = currentMapController;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character
    ) {
        MapHelper mapHelper = mapController.getMapHelper();

        return new RandomMovement(character, mapHelper);
    }

    protected void displayCharacter(final GameCharacter character) {
        mapController.addCharacter(character);
    }
}

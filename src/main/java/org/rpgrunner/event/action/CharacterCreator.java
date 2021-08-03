package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.RandomMovement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.helper.MapHelper;

public class CharacterCreator extends AbstractCharacterCreator {
    private final MapController mapController;

    public CharacterCreator(
        final MapController currentMapController,
        final CharacterAnimationFactory characterAnimationFactory,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final Action interactiveAction
    ) {
        super(
            currentMapController,
            characterAnimationFactory,
            newCharacterFileBaseName,
            initialMapPositionX,
            initialMapPositionY,
            interactiveAction
        );
        mapController = currentMapController;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character,
        final CharacterAnimation characterAnimation
    ) {
        MapHelper mapHelper = mapController.getMapHelper();

        return new RandomMovement(character, characterAnimation, mapHelper);
    }

    protected void displayCharacter(final CharacterElement characterElement) {
        mapController.addCharacterElement(characterElement);
    }
}

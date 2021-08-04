package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.helper.MapHelper;

public abstract class AbstractCharacterCreator implements Action {
    private final MapController mapController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final String characterFileBaseName;
    private final LocalTeleport localTeleport;
    private final Action interactiveAction;

    public AbstractCharacterCreator(
        final MapController currentMapController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final Action newInteractiveAction
    ) {
        mapController = currentMapController;
        characterFileBaseName = newCharacterFileBaseName;
        localTeleport = new LocalTeleport(
            initialMapPositionX,
            initialMapPositionY
        );
        characterAnimationFactory = currentCharacterAnimationFactory;
        interactiveAction = newInteractiveAction;
    }

    public void execute() {
        CharacterElement characterElement = generateCharacterElement();
        localTeleport.setCharacterElement(characterElement);
        localTeleport.execute();
        displayCharacter(characterElement);
    }

    private CharacterElement generateCharacterElement() {
        GameCharacter character = new GameCharacter(
            characterFileBaseName,
            interactiveAction
        );
        CharacterAnimation characterAnimation = (
            characterAnimationFactory.createCharacterAnimation(character)
        );
        MovementCommand movementCommand = createMovementCommand(
            character,
            characterAnimation
        );
        MapHelper mapHelper = mapController.getMapHelper();
        CharacterElement characterElement = new CharacterElement(
            character,
            characterAnimation,
            movementCommand
        );

        return characterElement;
    }

    protected abstract MovementCommand createMovementCommand(
        GameCharacter character,
        CharacterAnimation characterAnimation
    );

    protected abstract void displayCharacter(CharacterElement characterElement);
}

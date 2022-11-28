package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.helper.MapHelper;

public abstract class AbstractCharacterCreator implements Action {
    private final MapController mapController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final byte characterIDSprite;
    private final LocalTeleport localTeleport;
    private final CharacterEventListener characterEventListener;

    public AbstractCharacterCreator(
        final MapController currentMapController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
        final byte newCharacterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final CharacterEventListener newCharacterEventListener
    ) {
        mapController = currentMapController;
        characterIDSprite = newCharacterIDSprite;
        localTeleport = new LocalTeleport(
            initialMapPositionX,
            initialMapPositionY
        );
        characterAnimationFactory = currentCharacterAnimationFactory;
        characterEventListener = newCharacterEventListener;
    }

    public void execute() {
        CharacterElement characterElement = generateCharacterElement();
        localTeleport.setCharacterElement(characterElement);
        localTeleport.execute();
        displayCharacter(characterElement);
    }

    private CharacterElement generateCharacterElement() {
        CharacterAnimation characterAnimation = (
            characterAnimationFactory.createCharacterAnimation(
                characterIDSprite,
                characterEventListener
            )
        );
        MovementCommand movementCommand = createMovementCommand(
            characterAnimation,
            characterAnimation
        );
        MapHelper mapHelper = mapController.getMapHelper();
        CharacterElement characterElement = new CharacterElement(
            characterAnimation,
            characterAnimation,
            movementCommand
        );

        return characterElement;
    }

    protected abstract MovementCommand createMovementCommand(
        CharacterAnimation character,
        CharacterAnimation characterAnimation
    );

    protected abstract void displayCharacter(CharacterElement characterElement);
}

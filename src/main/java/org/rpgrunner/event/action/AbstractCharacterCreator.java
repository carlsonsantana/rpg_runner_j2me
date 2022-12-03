package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.helper.MapHelper;

public abstract class AbstractCharacterCreator implements Action {
    private final MapController mapController;
    private final CharacterFactory characterFactory;
    private final byte characterIDSprite;
    private final LocalTeleport localTeleport;
    private final CharacterEventListener characterEventListener;

    public AbstractCharacterCreator(
        final MapController currentMapController,
        final CharacterFactory currentCharacterFactory,
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
        characterFactory = currentCharacterFactory;
        characterEventListener = newCharacterEventListener;
    }

    public void execute() {
        CharacterElement characterElement = generateCharacterElement();
        localTeleport.setCharacterElement(characterElement);
        localTeleport.execute();
        displayCharacter(characterElement);
    }

    private CharacterElement generateCharacterElement() {
        GameCharacter character = characterFactory.createCharacter(
            characterIDSprite,
            characterEventListener
        );
        MovementCommand movementCommand = createMovementCommand(character);
        MapHelper mapHelper = mapController.getMapHelper();
        CharacterElement characterElement = new CharacterElement(
            character,
            movementCommand
        );

        return characterElement;
    }

    protected abstract MovementCommand createMovementCommand(
        GameCharacter character
    );

    protected abstract void displayCharacter(CharacterElement characterElement);
}

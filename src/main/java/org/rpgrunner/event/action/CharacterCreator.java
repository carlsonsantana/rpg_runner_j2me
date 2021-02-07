package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.RandomMovement;

public class CharacterCreator extends AbstractCharacterCreator {
    private final GameController gameController;

    public CharacterCreator(
        final GameController currentGameController,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        super(
            currentGameController,
            newCharacterFileBaseName,
            initialMapPositionX,
            initialMapPositionY
        );
        gameController = currentGameController;
    }

    public void displayCharacter(final CharacterElement characterElement) {
        gameController.addCharacterElement(characterElement);
    }

    public MovementCommand createMovementCommand(
        final GameCharacter character
    ) {
        return new RandomMovement(character);
    }
}

package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovementFactory;

public class PlayerCharacterCreator extends AbstractCharacterCreator {
    private final GameController gameController;

    public PlayerCharacterCreator(
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
        gameController.setPlayerCharacterElement(characterElement);
    }

    public MovementCommand createMovementCommand(
        final GameCharacter character
    ) {
        PlayerMovementFactory playerMovementFactory = (
            gameController.getPlayerMovementFactory()
        );

        return playerMovementFactory.createPlayerMovement(character);
    }
}

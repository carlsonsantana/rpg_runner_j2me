package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovementFactory;

public class PlayerCharacterCreator extends AbstractCharacterCreator {
    private final GameController gameController;
    private final PlayerMovementFactory playerMovementFactory;

    public PlayerCharacterCreator(
        final GameController currentGameController,
        final CharacterAnimationFactory characterAnimationFactory,
        final PlayerMovementFactory currentPlayerMovementFactory,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        super(
            currentGameController,
            characterAnimationFactory,
            newCharacterFileBaseName,
            initialMapPositionX,
            initialMapPositionY
        );
        gameController = currentGameController;
        playerMovementFactory = currentPlayerMovementFactory;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character
    ) {
        return playerMovementFactory.createPlayerMovement(character);
    }

    protected void displayCharacter(final CharacterElement characterElement) {
        gameController.setPlayerCharacterElement(characterElement);
    }
}

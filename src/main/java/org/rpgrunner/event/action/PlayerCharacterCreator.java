package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.helper.CollisionDetector;

public class PlayerCharacterCreator implements Action {
    private final GameController gameController;
    private final String characterFileBaseName;
    private final LocalTeleport localTeleport;

    public PlayerCharacterCreator(
        final GameController currentGameController,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        gameController = currentGameController;
        characterFileBaseName = newCharacterFileBaseName;
        localTeleport = new LocalTeleport(
            initialMapPositionX,
            initialMapPositionY
        );
    }

    public void execute() {
        CharacterElement playerCharacterElement = (
            generatePlayerCharacterElement()
        );
        gameController.setPlayerCharacterElement(playerCharacterElement);
        localTeleport.setCharacterElement(playerCharacterElement);
        localTeleport.execute();
    }

    private CharacterElement generatePlayerCharacterElement() {
        GameCharacter character = new GameCharacter(characterFileBaseName);
        PlayerMovementFactory playerMovementFactory = (
            gameController.getPlayerMovementFactory()
        );
        PlayerMovement playerMovement = (
            playerMovementFactory.createPlayerMovement(character)
        );
        CharacterElement characterElement = generateCharacterElement(
            character,
            playerMovement
        );

        return characterElement;
    }

    private CharacterElement generateCharacterElement(
        final GameCharacter character,
        final MovementCommand movementCommand
    ) {
        CharacterAnimationFactory characterAnimationFactory = (
            gameController.getCharacterAnimationFactory()
        );
        CollisionDetector collisionDetector = (
            gameController.getCollisionDetector()
        );
        CharacterAnimation characterAnimation = (
            characterAnimationFactory.createCharacterAnimation(character)
        );
        CharacterElement characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation,
            movementCommand
        );
        character.setCharacterElement(characterElement);
        characterAnimation.setCharacterElement(characterElement);

        return characterElement;
    }
}

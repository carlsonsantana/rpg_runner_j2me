package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.helper.CollisionDetector;

public abstract class AbstractCharacterCreator implements Action {
    private final GameController gameController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final String characterFileBaseName;
    private final LocalTeleport localTeleport;

    public AbstractCharacterCreator(
        final GameController currentGameController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
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
        characterAnimationFactory = currentCharacterAnimationFactory;
    }

    public void execute() {
        CharacterElement characterElement = generateCharacterElement();
        localTeleport.setCharacterElement(characterElement);
        localTeleport.execute();
        displayCharacter(characterElement);
    }

    public abstract void displayCharacter(CharacterElement characterElement);

    private CharacterElement generateCharacterElement() {
        GameCharacter character = new GameCharacter(characterFileBaseName);
        MovementCommand movementCommand = createMovementCommand(character);
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

    public abstract MovementCommand createMovementCommand(
        GameCharacter character
    );
}

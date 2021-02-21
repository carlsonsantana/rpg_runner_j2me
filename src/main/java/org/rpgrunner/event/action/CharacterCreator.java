package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.RandomMovement;

public class CharacterCreator extends AbstractCharacterCreator {
    private final GameController gameController;

    public CharacterCreator(
        final GameController currentGameController,
        final CharacterAnimationFactory characterAnimationFactory,
        final String newCharacterFileBaseName,
        final int initialMapPositionX,
        final int initialMapPositionY,
        final Action interactiveAction
    ) {
        super(
            currentGameController,
            characterAnimationFactory,
            newCharacterFileBaseName,
            initialMapPositionX,
            initialMapPositionY,
            interactiveAction
        );
        gameController = currentGameController;
    }

    protected MovementCommand createMovementCommand(
        final GameCharacter character
    ) {
        return new RandomMovement(character);
    }

    protected void displayCharacter(final CharacterElement characterElement) {
        gameController.addCharacterElement(characterElement);
    }
}

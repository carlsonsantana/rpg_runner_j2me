package org.rpgrunner.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.helper.Input;
import org.rpgrunner.helper.MapHelper;

public class PlayerMovement extends AbstractMovement {
    private final GameCharacter character;
    private final MapHelper mapHelper;
    private final Input input;

    public PlayerMovement(
        final GameCharacter playerCharacter,
        final CharacterAnimation characterAnimation,
        final MapHelper newMapHelper,
        final Input currentInput
    ) {
        super(playerCharacter, characterAnimation, newMapHelper);

        character = playerCharacter;
        mapHelper = newMapHelper;
        input = currentInput;
    }

    public void execute() {
        if (input.isActionPressed()) {
            mapHelper.executeInteractAction(character);
        }

        super.execute();
    }

    protected void executeMovement() {
        if (input.isHoldingUp()) {
            character.moveUp();
        } else if (input.isHoldingRight()) {
            character.moveRight();
        } else if (input.isHoldingDown()) {
            character.moveDown();
        } else if (input.isHoldingLeft()) {
            character.moveLeft();
        }
    }
}

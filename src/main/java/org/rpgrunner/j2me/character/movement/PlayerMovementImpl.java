package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.AbstractMovement;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.helper.Input;
import org.rpgrunner.helper.MapHelper;

public class PlayerMovementImpl extends AbstractMovement implements
    PlayerMovement {
    private final GameCharacter character;
    private final MapHelper mapHelper;
    private final Input input;

    public PlayerMovementImpl(
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

    public void pressKey(final int keyPressed) { }

    public void releaseKey(final int keyReleased) { }

    public void releaseAllKeys() { }
}

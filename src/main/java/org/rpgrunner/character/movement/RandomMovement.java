package org.rpgrunner.character.movement;

import java.util.Random;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.helper.MapHelper;

public class RandomMovement extends AbstractMovement {
    private final CharacterAnimation character;
    private final Random random;

    public RandomMovement(
        final CharacterAnimation controlledCharacter,
        final MapHelper mapHelper
    ) {
        super(controlledCharacter, mapHelper);

        character = controlledCharacter;
        random = new Random();
    }

    protected void executeMovement() {
        int direction = random.nextInt(Direction.NUMBER_DIRECTIONS);

        if (direction == 0) {
            character.moveUp();
        } else if (direction == 1) {
            character.moveRight();
        } else if (direction == 2) {
            character.moveDown();
        } else {
            character.moveLeft();
        }
    }
}

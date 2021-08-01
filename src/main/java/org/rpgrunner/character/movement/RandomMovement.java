package org.rpgrunner.character.movement;

import java.util.Random;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;

public class RandomMovement extends AbstractMovement {
    private final GameCharacter character;
    private final Random random;

    public RandomMovement(final GameCharacter controlledCharacter) {
        super(controlledCharacter);

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

package org.rpgrunner.character.command;

import java.util.Random;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;

public class RandomMovimentCommand implements MovimentCommand {
    private final GameCharacter character;
    private final Random random;

    public RandomMovimentCommand(final GameCharacter controlledCharacter) {
        character = controlledCharacter;
        random = new Random();
    }

    public void execute() {
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

package org.rpgrunner.character.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.SimpleCharacter;

public class RandomMovimentCommandTest extends TestCase {
    public void testCharacterCanMoveAllDirections() {
        SimpleCharacter character = new SimpleCharacter();
        RandomMovimentCommand randomMovimentCommand = new RandomMovimentCommand(
            character
        );
        boolean moveUp = false;
        boolean moveRight = false;
        boolean moveDown = false;
        boolean moveLeft = false;
        boolean moveAllDirections = false;

        for (int i = 0; i < 1000; i++) {
            randomMovimentCommand.execute();

            byte direction = character.getDirection();
            moveUp = moveUp || Direction.isUp(direction);
            moveRight = moveRight || Direction.isRight(direction);
            moveDown = moveDown || Direction.isDown(direction);
            moveLeft = moveLeft || Direction.isLeft(direction);

            moveAllDirections = moveUp && moveRight && moveDown && moveLeft;

            if (moveAllDirections) {
                break;
            }
        }

        Assert.assertTrue(moveAllDirections);
    }

    public void testCharacterRandomMoviments() {
        SimpleCharacter character1 = new SimpleCharacter();
        SimpleCharacter character2 = new SimpleCharacter();
        RandomMovimentCommand randomMovimentCommandCharacter1 = (
            new RandomMovimentCommand(character1)
        );
        RandomMovimentCommand randomMovimentCommandCharacter2 = (
            new RandomMovimentCommand(character2)
        );
        boolean directionsEquals = true;

        for (int i = 0; i < 100; i++) {
            randomMovimentCommandCharacter1.execute();
            randomMovimentCommandCharacter2.execute();

            directionsEquals = (
                directionsEquals
                && (character1.getDirection() == character2.getDirection())
            );
        }

        Assert.assertFalse(directionsEquals);
    }
}

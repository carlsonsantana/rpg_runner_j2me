package org.rpgrunner.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.SimpleCharacter;

public class RandomCommandTest extends TestCase {
    public void testCharacterCanMoveAllDirections() {
        SimpleCharacter character = new SimpleCharacter();
        RandomCommand randomCommand = new RandomCommand(character);
        boolean moveUp = false;
        boolean moveRight = false;
        boolean moveDown = false;
        boolean moveLeft = false;
        boolean moveAllDirections = false;

        for (int i = 0; i < 1000; i++) {
            randomCommand.execute();

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
        RandomCommand randomCommandCharacter1 = new RandomCommand(character1);
        RandomCommand randomCommandCharacter2 = new RandomCommand(character2);
        boolean directionsEquals = true;

        for (int i = 0; i < 100; i++) {
            randomCommandCharacter1.execute();
            randomCommandCharacter2.execute();

            directionsEquals = (
                directionsEquals
                && (character1.getDirection() == character2.getDirection())
            );
        }

        Assert.assertFalse(directionsEquals);
    }
}

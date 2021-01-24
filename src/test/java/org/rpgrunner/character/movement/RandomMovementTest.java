package org.rpgrunner.character.movement;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.SimpleCharacter;

public class RandomMovementTest extends TestCase {
    public void testCharacterCanMoveAllDirections() {
        SimpleCharacter character = new SimpleCharacter();
        RandomMovement randomMovement = new RandomMovement(character);
        boolean moveUp = false;
        boolean moveRight = false;
        boolean moveDown = false;
        boolean moveLeft = false;
        boolean moveAllDirections = false;

        for (int i = 0; i < 1000; i++) {
            randomMovement.execute();

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
        RandomMovement randomMovement1 = new RandomMovement(character1);
        RandomMovement randomMovement2 = new RandomMovement(character2);
        boolean directionsEquals = true;

        for (int i = 0; i < 100; i++) {
            randomMovement1.execute();
            randomMovement2.execute();

            directionsEquals = (
                directionsEquals
                && (character1.getDirection() == character2.getDirection())
            );
        }

        Assert.assertFalse(directionsEquals);
    }
}

package org.rpgrunner.character.movement;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.character.SimpleCharacter;

public class RandomMovementTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 1000;

    public void testCharacterCanMoveAllDirections() {
        SimpleCharacter character = new SimpleCharacter();
        RandomMovement randomMovement = new RandomMovement(character);
        boolean moveUp = false;
        boolean moveRight = false;
        boolean moveDown = false;
        boolean moveLeft = false;
        boolean moveAllDirections = false;

        for (int i = 0; (i < TEST_REPEAT_LOOP) && (!moveAllDirections); i++) {
            randomMovement.execute();

            byte direction = character.getDirection();
            moveUp = moveUp || Direction.isUp(direction);
            moveRight = moveRight || Direction.isRight(direction);
            moveDown = moveDown || Direction.isDown(direction);
            moveLeft = moveLeft || Direction.isLeft(direction);

            moveAllDirections = moveUp && moveRight && moveDown && moveLeft;
        }

        Assert.assertTrue(moveAllDirections);
    }

    public void testCharacterRandomMovements() {
        SimpleCharacter character1 = new SimpleCharacter();
        SimpleCharacter character2 = new SimpleCharacter();
        RandomMovement randomMovement1 = new RandomMovement(character1);
        RandomMovement randomMovement2 = new RandomMovement(character2);
        boolean directionsEquals = true;

        for (int i = 0; (i < TEST_REPEAT_LOOP) && (directionsEquals); i++) {
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

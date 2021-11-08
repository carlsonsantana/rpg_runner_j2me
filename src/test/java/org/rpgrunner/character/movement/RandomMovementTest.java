package org.rpgrunner.character.movement;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class RandomMovementTest extends TestCase implements MovementTest {
    private static final int TEST_REPEAT_LOOP = 1000;
    private MapHelperSpy mapHelper;

    public void setUp() {
        mapHelper = new MapHelperSpy();
    }

    public void testCharacterCanMoveAllDirections() {
        boolean moveUp = false;
        boolean moveRight = false;
        boolean moveDown = false;
        boolean moveLeft = false;
        boolean moveAllDirections = false;

        for (int i = 0; (i < TEST_REPEAT_LOOP) && (!moveAllDirections); i++) {
            SimpleCharacter character = new SimpleCharacter();
            CharacterAnimationSpy characterAnimation = (
                new CharacterAnimationSpy()
            );
            RandomMovement randomMovement = new RandomMovement(
                character,
                characterAnimation,
                mapHelper
            );

            randomMovement.execute();

            byte direction = character.getDirection();
            moveUp = moveUp || Direction.isUp(direction);
            moveRight = moveRight || Direction.isRight(direction);
            moveDown = moveDown || Direction.isDown(direction);
            moveLeft = moveLeft || Direction.isLeft(direction);
            moveAllDirections = moveUp && moveRight && moveDown && moveLeft;

            Assert.assertTrue(characterAnimation.isStartAnimationCalled());
        }

        Assert.assertTrue(moveAllDirections);
    }

    public void testCharacterRandomMovements() {
        boolean directionsEquals = true;

        for (int i = 0; (i < TEST_REPEAT_LOOP) && (directionsEquals); i++) {
            SimpleCharacter character1 = new SimpleCharacter();
            SimpleCharacter character2 = new SimpleCharacter();
            RandomMovement randomMovement1 = createRandomMovement(character1);
            RandomMovement randomMovement2 = createRandomMovement(character2);

            randomMovement1.execute();
            randomMovement2.execute();

            directionsEquals = (
                character1.getDirection() == character2.getDirection()
            );
        }

        Assert.assertFalse(directionsEquals);
    }

    public void testDoNotMoveWhenCharacterIsMoving() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkDoNotMoveWhenCharacterIsMoving();
        }
    }

    private void checkDoNotMoveWhenCharacterIsMoving() {
        SimpleCharacter character = new SimpleCharacter();
        RandomMovement randomMovement = createRandomMovement(character);

        byte initialDirection = character.getDirection();

        character.setMoving(true);
        randomMovement.execute();

        Assert.assertEquals(initialDirection, character.getDirection());
    }

    public void testCancelMoveWhenCharacterCantMove() {
        mapHelper.setCanMove(false);

        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCancelMoveWhenCharacterCantMove();
        }
    }

    private void checkCancelMoveWhenCharacterCantMove() {
        SimpleCharacter character = new SimpleCharacter();
        RandomMovement randomMovement = createRandomMovement(character);
        randomMovement.execute();

        Assert.assertFalse(character.isMoving());
    }

    private RandomMovement createRandomMovement(final GameCharacter character) {
        CharacterAnimation characterAnimation = new CharacterAnimationSpy();
        RandomMovement randomMovement = new RandomMovement(
            character,
            characterAnimation,
            mapHelper
        );

        return randomMovement;
    }
}

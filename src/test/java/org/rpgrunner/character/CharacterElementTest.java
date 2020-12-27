package org.rpgrunner.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.command.Command;
import org.rpgrunner.command.RandomCommand;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.CollisionDetectorSpy;

public class CharacterElementTest extends TestCase {
    private CharacterElement characterElement;
    private CollisionDetectorSpy collisionDetector;
    private GameCharacter character;
    private CharacterAnimationSpy characterAnimation;
    private Command command;

    public void setUp() {
        collisionDetector = new CollisionDetectorSpy();
        character = RandomGenerator.generateRandomCharacter();
        characterAnimation = new CharacterAnimationSpy();
        command = new RandomCommand(character);

        characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation,
            command
        );
    }

    public void testReturnSameCharacter() {
        Assert.assertSame(character, characterElement.getCharacter());
    }

    public void testReturnSameCharacterAnimation() {
        Assert.assertSame(
            characterAnimation,
            characterElement.getCharacterAnimation()
        );
    }

    public void testReturnSameCommand() {
        Assert.assertSame(command, characterElement.getCommand());
    }

    public void testOnMoveFalseCancelCharacterMovement() {
        collisionDetector.setCanMove(false);
        int y = character.getMapPositionY();

        character.moveUp();
        characterElement.onMove();

        Assert.assertFalse(character.isMoving());
        Assert.assertEquals(y, character.getMapPositionY());
        Assert.assertEquals(y, character.getMapNextPositionY());
        Assert.assertTrue(characterAnimation.isStartAnimationCalled());
    }

    public void testOnMoveTrueContinueCharacterMovement() {
        collisionDetector.setCanMove(true);
        int y = character.getMapPositionY();
        int nextY = y - 1;

        character.moveUp();
        characterElement.onMove();

        Assert.assertTrue(character.isMoving());
        Assert.assertEquals(y, character.getMapPositionY());
        Assert.assertEquals(nextY, character.getMapNextPositionY());
        Assert.assertTrue(characterAnimation.isStartAnimationCalled());
    }

    public void testOnAnimationCompleteFinishCharacterMovement() {
        collisionDetector.setCanMove(true);
        int y = character.getMapPositionY();
        int nextY = y - 1;

        character.moveUp();
        characterElement.onAnimationComplete();

        Assert.assertFalse(character.isMoving());
        Assert.assertEquals(nextY, character.getMapPositionY());
    }
}

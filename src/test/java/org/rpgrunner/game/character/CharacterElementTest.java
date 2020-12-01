package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.CollisionDetectorSpy;

public class CharacterElementTest extends TestCase {
    private CharacterElement characterElement;
    private CollisionDetectorSpy collisionDetector;
    private GameCharacter character;
    private CharacterAnimationSpy characterAnimation;

    public void setUp() {
        collisionDetector = new CollisionDetectorSpy();
        character = RandomGenerator.generateRandomCharacter();
        characterAnimation = new CharacterAnimationSpy();

        characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation
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

    public void testOnMoveFalseCancelCharacterMovement() {
        int x = character.getMapPositionX();
        int y = character.getMapPositionY();
        int nextX = character.getMapNextPositionX();
        int nextY = character.getMapNextPositionY();

        Assert.assertFalse(character.isMoving());

        character.moveUp();

        Assert.assertTrue(character.isMoving());

        collisionDetector.setCanMove(false);
        characterElement.onMove();

        Assert.assertFalse(character.isMoving());
        Assert.assertEquals(x, character.getMapPositionX());
        Assert.assertEquals(y, character.getMapPositionY());
        Assert.assertEquals(nextX, character.getMapNextPositionX());
        Assert.assertEquals(nextY, character.getMapNextPositionY());
        Assert.assertTrue(characterAnimation.isStartAnimationCalled());
    }

    public void testOnMoveTrueContinueCharacterMovement() {
        int x = character.getMapPositionX();
        int y = character.getMapPositionY();

        Assert.assertFalse(character.isMoving());

        character.moveUp();
        int nextX = character.getMapNextPositionX();
        int nextY = character.getMapNextPositionY();

        Assert.assertTrue(character.isMoving());

        collisionDetector.setCanMove(true);
        characterElement.onMove();

        Assert.assertTrue(character.isMoving());
        Assert.assertEquals(x, character.getMapPositionX());
        Assert.assertEquals(y, character.getMapPositionY());
        Assert.assertEquals(nextX, character.getMapNextPositionX());
        Assert.assertEquals(nextY, character.getMapNextPositionY());
        Assert.assertTrue(characterAnimation.isStartAnimationCalled());
    }
}

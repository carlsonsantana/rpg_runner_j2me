package org.rpgrunner.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.RandomMovement;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class CharacterElementTest extends TestCase {
    private CharacterElement characterElement;
    private MapHelperSpy mapHelper;
    private CharacterAnimation character;
    private CharacterAnimationSpy characterAnimation;
    private MovementCommand movementCommand;

    public void setUp() {
        mapHelper = new MapHelperSpy();
        character = RandomGenerator.generateRandomCharacter();
        characterAnimation = new CharacterAnimationSpy();
        movementCommand = new RandomMovement(
            character,
            characterAnimation,
            mapHelper
        );

        characterElement = new CharacterElement(
            character,
            characterAnimation,
            movementCommand
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

    public void testReturnSameMovementCommand() {
        Assert.assertSame(
            movementCommand,
            characterElement.getMovementCommand()
        );
    }
}

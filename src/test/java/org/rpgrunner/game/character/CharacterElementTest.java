package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.CharacterMovementEventSpy;

public class CharacterElementTest extends TestCase {
    public void testReturnSameCharacter() {
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterElement characterElement = new CharacterElement(
            null,
            character,
            null
        );
        Assert.assertSame(character, characterElement.getCharacter());
    }

    public void testReturnSameCharacterAnimation() {
        CharacterAnimation characterAnimationSpy = new CharacterAnimationSpy();
        CharacterElement characterElement = new CharacterElement(
            null,
            null,
            characterAnimationSpy
        );
        Assert.assertSame(
            characterAnimationSpy,
            characterElement.getCharacterAnimation()
        );
    }

    public void testOnMoveFalseCancelCharacterMoviment() {
        CharacterMovimentEvent event = new CharacterMovementEventSpy(false);
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterAnimation characterAnimationSpy = new CharacterAnimationSpy();

        CharacterElement characterElement = new CharacterElement(
            event,
            character,
            characterAnimationSpy
        );

        int x = character.getMapPositionX();
        int y = character.getMapPositionY();
        int nextX = character.getMapNextPositionX();
        int nextY = character.getMapNextPositionY();

        Assert.assertFalse(character.isMoving());

        character.moveUp();

        Assert.assertTrue(character.isMoving());

        characterElement.onMove();

        Assert.assertFalse(character.isMoving());
        Assert.assertEquals(x, character.getMapPositionX());
        Assert.assertEquals(y, character.getMapPositionY());
        Assert.assertEquals(nextX, character.getMapNextPositionX());
        Assert.assertEquals(nextY, character.getMapNextPositionY());
    }
}

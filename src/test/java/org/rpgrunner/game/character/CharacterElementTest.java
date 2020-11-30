package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.CharacterAnimationSpy;

public class CharacterElementTest extends TestCase {
    public void testReturnSameCharacter() {
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterElement characterElement = new CharacterElement(
            character,
            null
        );
        Assert.assertSame(character, characterElement.getCharacter());
    }

    public void testReturnSameCharacterAnimation() {
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterAnimation characterAnimationSpy = new CharacterAnimationSpy();
        CharacterElement characterElement = new CharacterElement(
            character,
            characterAnimationSpy
        );
        Assert.assertSame(
            characterAnimationSpy,
            characterElement.getCharacterAnimation()
        );
    }
}

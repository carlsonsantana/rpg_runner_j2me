package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;

public class CharacterElementTest extends TestCase {
    public void testReturnSameCharacter() {
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterElement characterElement = new CharacterElement(character);
        Assert.assertSame(character, characterElement.getCharacter());
    }
}

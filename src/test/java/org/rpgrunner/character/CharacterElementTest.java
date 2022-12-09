package org.rpgrunner.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.character.CharacterSpy;

public class CharacterElementTest extends TestCase {
    private CharacterElement characterElement;
    private CharacterSpy character;

    public void setUp() {
        character = new CharacterSpy();

        characterElement = new CharacterElement(character);
    }

    public void testReturnSameCharacter() {
        Assert.assertSame(character, characterElement.getCharacterAnimation());
    }
}

package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.CollisionDetectorSpy;

public class CharacterElementTest extends TestCase {
    public void testReturnSameCharacterMovimentEvent() {
        CollisionDetectorSpy collisionDetector = new CollisionDetectorSpy();
        CharacterMovimentEvent event = new CharacterMovimentEvent(
            collisionDetector
        );
        CharacterElement characterElement = new CharacterElement(
            event,
            null,
            null
        );
        Assert.assertSame(event, characterElement.getCharacterMovimentEvent());
    }

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
}

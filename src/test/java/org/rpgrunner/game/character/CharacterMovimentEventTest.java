package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.CollisionDetectorSpy;
import org.rpgrunner.test.mock.CharacterSpy;
import org.rpgrunner.test.mock.CharacterAnimationSpy;

public class CharacterMovimentEventTest extends TestCase {
    private CharacterMovimentEvent characterMovimentEvent;
    private CollisionDetectorSpy collisionDetector;

    public void setUp() {
        collisionDetector = new CollisionDetectorSpy();
        characterMovimentEvent = new CharacterMovimentEvent(collisionDetector);
    }

    public void testReturnSameCollisionDetector() {
        Assert.assertSame(
            collisionDetector,
            characterMovimentEvent.getCollisionDetector()
        );
    }

    public void testReturnSameValueOfCanMove() {
        CharacterSpy character = new CharacterSpy(null);
        CharacterAnimation characterAnimation = new CharacterAnimationSpy();
        CharacterElement characterElement = new CharacterElement(
            characterMovimentEvent,
            character,
            characterAnimation
        );

        collisionDetector.setCanMove(true);
        Assert.assertTrue(characterMovimentEvent.onMove(characterElement));

        collisionDetector.setCanMove(false);
        Assert.assertFalse(characterMovimentEvent.onMove(characterElement));
    }
}

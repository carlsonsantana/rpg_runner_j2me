package org.rpgrunner.game.helper;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.CollisionDetectorSpy;
import org.rpgrunner.test.mock.CharacterSpy;

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

        collisionDetector.setCanMove(true);
        Assert.assertTrue(characterMovimentEvent.onMove(character));

        collisionDetector.setCanMove(false);
        Assert.assertFalse(characterMovimentEvent.onMove(character));
    }
}

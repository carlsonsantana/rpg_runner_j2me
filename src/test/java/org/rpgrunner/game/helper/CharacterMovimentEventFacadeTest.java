package org.rpgrunner.game.helper;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.CollisionDetectorSpy;
import org.rpgrunner.test.mock.CharacterSpy;

public class CharacterMovimentEventFacadeTest extends TestCase {
    private CollisionDetectorSpy collisionDetector;

    public void setUp() {
        CharacterMovimentEventFacade.setCollisionDetector(null);
        collisionDetector = new CollisionDetectorSpy();
    }

    public void testReturnSameCollisionDetector() {
        CharacterMovimentEventFacade.setCollisionDetector(collisionDetector);
        Assert.assertSame(
            collisionDetector,
            CharacterMovimentEventFacade.getCollisionDetector()
        );
    }

    public void testReturnSameValueOfCanMove() {
        CharacterMovimentEventFacade.setCollisionDetector(collisionDetector);
        CharacterSpy character = new CharacterSpy(null);

        collisionDetector.setCanMove(true);
        Assert.assertTrue(CharacterMovimentEventFacade.onMove(character));

        collisionDetector.setCanMove(false);
        Assert.assertFalse(CharacterMovimentEventFacade.onMove(character));
    }
}

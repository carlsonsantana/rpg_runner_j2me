package org.rpgrunner.game.helper;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.CollisionDetectorSpy;

public class CharacterMovimentEventFacadeTest extends TestCase {
    public void testReturnSameCollisionDetector() {
        CollisionDetector collisionDetector = new CollisionDetectorSpy();
        CharacterMovimentEventFacade.setCollisionDetector(collisionDetector);
        Assert.assertSame(
            collisionDetector,
            CharacterMovimentEventFacade.getCollisionDetector()
        );
    }
}

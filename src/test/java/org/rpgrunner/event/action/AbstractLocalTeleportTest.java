package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;

public abstract class AbstractLocalTeleportTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private SimpleCharacter character;
    private CharacterAnimationSpy characterAnimation;
    private CharacterElement characterElement;

    public void setUp() {
        character = new SimpleCharacter();
        characterAnimation = new CharacterAnimationSpy();
        characterElement = new CharacterElement(
            character,
            characterAnimation,
            null
        );
    }

    public void testChangeCharacterPositionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkChangeCharacterPosition();
        }
    }

    private void checkChangeCharacterPosition() {
        int mapPositionX = RandomGenerator.getRandomPosition();
        int mapPositionY = RandomGenerator.getRandomPosition();
        LocalTeleport localTeleport = createLocalTeleport(
            mapPositionX,
            mapPositionY
        );

        localTeleport.setCharacterElement(characterElement);
        localTeleport.execute();

        Assert.assertTrue(
            characterAnimation.isUpdateScreenPositionFromMapPositionCalled()
        );
        Assert.assertEquals(mapPositionX, character.getMapPositionX());
        Assert.assertEquals(mapPositionY, character.getMapPositionY());
        Assert.assertEquals(mapPositionX, character.getMapNextPositionX());
        Assert.assertEquals(mapPositionY, character.getMapNextPositionY());
    }

    protected abstract LocalTeleport createLocalTeleport(
        int newMapPositionX,
        int newMapPositionY
    );
}

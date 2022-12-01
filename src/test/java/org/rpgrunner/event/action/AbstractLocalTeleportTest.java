package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;

public abstract class AbstractLocalTeleportTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private CharacterAnimationSpy character;
    private CharacterElement characterElement;

    public void setUp() {
        character = new CharacterAnimationSpy();
        characterElement = new CharacterElement(character, character, null);
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
            character.isUpdateScreenPositionFromMapPositionCalled()
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

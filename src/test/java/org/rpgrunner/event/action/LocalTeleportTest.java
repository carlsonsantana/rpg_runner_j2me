package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;

public class LocalTeleportTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private Random random;
    private SimpleCharacter character;
    private CharacterAnimationSpy characterAnimation;
    private CharacterElement characterElement;

    public LocalTeleportTest() {
        random = new Random();
    }

    public void setUp() {
        character = new SimpleCharacter();
        characterAnimation = new CharacterAnimationSpy();
        characterElement = new CharacterElement(
            null,
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
        int mapPositionX = random.nextInt(1000);
        int mapPositionY = random.nextInt(1000);
        LocalTeleport localTeleport = new LocalTeleport(
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
}

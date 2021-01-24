package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.action.LocalTeleport;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.test.mock.SimpleCharacter;
import org.rpgrunner.test.mock.CharacterAnimationSpy;

public class LocalTeleportTest extends TestCase {
    public void testChangeCharacterPosition() {
        Random random = new Random();
        SimpleCharacter character = new SimpleCharacter();
        CharacterAnimationSpy characterAnimation = new CharacterAnimationSpy();
        CharacterElement characterElement = new CharacterElement(
            null,
            character,
            characterAnimation,
            null
        );

        for (int i = 0; i < 100; i++) {
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
}

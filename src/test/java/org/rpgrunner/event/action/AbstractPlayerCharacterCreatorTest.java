package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;

public abstract class AbstractPlayerCharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private final Random random;

    public AbstractPlayerCharacterCreatorTest() {
        random = new Random();
    }

    public void testCreatePlayerCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreatePlayerCharacter();
        }
    }

    private void checkCreatePlayerCharacter() {
        MapController mapController = new MapControllerSpy();
        byte randomIDSprite = (byte) random.nextInt(Short.MAX_VALUE);
        int initialMapPositionX = RandomGenerator.getRandomPosition();
        int initialMapPositionY = RandomGenerator.getRandomPosition();
        PlayerCharacterCreator playerCharacterCreator = (
            createPlayerCharacterCreator(
                mapController,
                randomIDSprite,
                initialMapPositionX,
                initialMapPositionY
            )
        );

        playerCharacterCreator.execute();

        CharacterElement playerCharacterElement = (
            mapController.getPlayerCharacterElement()
        );
        GameCharacter playerCharacter = playerCharacterElement.getCharacter();

        Assert.assertEquals(randomIDSprite, playerCharacter.getIDSprite());
        Assert.assertEquals(
            initialMapPositionX,
            playerCharacter.getMapPositionX()
        );
        Assert.assertEquals(
            initialMapPositionY,
            playerCharacter.getMapPositionY()
        );
    }

    protected abstract PlayerCharacterCreator createPlayerCharacterCreator(
        MapController mapController,
        byte characterIDSprite,
        int initialMapPositionX,
        int initialMapPositionY
    );
}

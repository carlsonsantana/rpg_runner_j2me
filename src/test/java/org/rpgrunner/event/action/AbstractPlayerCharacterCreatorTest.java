package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;

public abstract class AbstractPlayerCharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;

    public void testCreatePlayerCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreatePlayerCharacter();
        }
    }

    private void checkCreatePlayerCharacter() {
        MapController mapController = new MapControllerSpy();
        String randomFileBaseName = RandomGenerator.getRandomString();
        int initialMapPositionX = RandomGenerator.getRandomPosition();
        int initialMapPositionY = RandomGenerator.getRandomPosition();
        PlayerCharacterCreator playerCharacterCreator = (
            createPlayerCharacterCreator(
                mapController,
                randomFileBaseName,
                initialMapPositionX,
                initialMapPositionY
            )
        );

        playerCharacterCreator.execute();

        CharacterElement playerCharacterElement = (
            mapController.getPlayerCharacterElement()
        );
        GameCharacter playerCharacter = playerCharacterElement.getCharacter();

        Assert.assertEquals(
            randomFileBaseName,
            playerCharacter.getFileBaseName()
        );
        Assert.assertEquals(
            initialMapPositionX,
            playerCharacter.getMapPositionX()
        );
        Assert.assertEquals(
            initialMapPositionY,
            playerCharacter.getMapPositionY()
        );
        Assert.assertNull(playerCharacter.getInteractiveAction());
    }

    protected abstract PlayerCharacterCreator createPlayerCharacterCreator(
        MapController mapController,
        String characterFileName,
        int initialMapPositionX,
        int initialMapPositionY
    );
}

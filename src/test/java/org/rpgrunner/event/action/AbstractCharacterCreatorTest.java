package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;

public abstract class AbstractCharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;

    public void testCreateCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreateCharacter();
        }
    }

    private void checkCreateCharacter() {
        MapControllerSpy mapController = new MapControllerSpy();
        String randomFileBaseName = RandomGenerator.getRandomString();
        int initialMapPositionX = RandomGenerator.getRandomPosition();
        int initialMapPositionY = RandomGenerator.getRandomPosition();
        CharacterCreator characterCreator = createCharacterCreator(
            mapController,
            randomFileBaseName,
            initialMapPositionX,
            initialMapPositionY
        );

        characterCreator.execute();

        CharacterElement characterElement = (
            mapController.getLastCharacterElementAdded()
        );
        GameCharacter character = characterElement.getCharacter();
        Action action = character.getInteractiveAction(Direction.UP);

        Assert.assertEquals(randomFileBaseName, character.getFileBaseName());
        Assert.assertEquals(initialMapPositionX, character.getMapPositionX());
        Assert.assertEquals(initialMapPositionY, character.getMapPositionY());
        Assert.assertNotNull(action);
        Assert.assertTrue(action instanceof NullAction);
    }

    protected abstract CharacterCreator createCharacterCreator(
        MapController mapController,
        String characterFileName,
        int initialMapPositionX,
        int initialMapPositionY
    );
}

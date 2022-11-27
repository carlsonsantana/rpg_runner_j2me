package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.controller.MapControllerSpy;

public abstract class AbstractCharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private final Random random;

    public AbstractCharacterCreatorTest() {
        random = new Random();
    }

    public void testCreateCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreateCharacter();
        }
    }

    private void checkCreateCharacter() {
        MapControllerSpy mapController = new MapControllerSpy();
        byte randomIDSprite = (byte) random.nextInt(Byte.MAX_VALUE);
        int initialMapPositionX = RandomGenerator.getRandomPosition();
        int initialMapPositionY = RandomGenerator.getRandomPosition();
        CharacterCreator characterCreator = createCharacterCreator(
            mapController,
            randomIDSprite,
            initialMapPositionX,
            initialMapPositionY
        );

        characterCreator.execute();

        CharacterElement characterElement = (
            mapController.getLastCharacterElementAdded()
        );
        CharacterAnimation character = characterElement.getCharacter();
        Action action = character.getInteractiveAction(Direction.UP);

        Assert.assertEquals(randomIDSprite, character.getIDSprite());
        Assert.assertEquals(initialMapPositionX, character.getMapPositionX());
        Assert.assertEquals(initialMapPositionY, character.getMapPositionY());
        Assert.assertNotNull(action);
        Assert.assertTrue(action instanceof NullAction);
    }

    protected abstract CharacterCreator createCharacterCreator(
        MapController mapController,
        byte characterIDSprite,
        int initialMapPositionX,
        int initialMapPositionY
    );
}

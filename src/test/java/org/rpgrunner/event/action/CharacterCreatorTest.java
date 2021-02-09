package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class CharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MINIMUM_INITIAL_POSITION = 2;
    private static final int MAXIMUM_INITIAL_POSITION = 100;
    private final Random random;

    public CharacterCreatorTest() {
        random = new Random();
    }

    public void testCreateCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreateCharacter();
        }
    }

    private void checkCreateCharacter() {
        GameControllerSpy gameController = new GameControllerSpy();
        String randomFileBaseName = RandomGenerator.getRandomString();
        int initialMapPositionX = (
            random.nextInt(MAXIMUM_INITIAL_POSITION)
            + MINIMUM_INITIAL_POSITION
        );
        int initialMapPositionY = (
            random.nextInt(MAXIMUM_INITIAL_POSITION)
            + MINIMUM_INITIAL_POSITION
        );
        CharacterCreator characterCreator = new CharacterCreator(
            gameController,
            randomFileBaseName,
            initialMapPositionX,
            initialMapPositionY
        );

        characterCreator.execute();

        CharacterElement characterElement = (
            gameController.getLastCharacterElementAdded()
        );
        GameCharacter character = characterElement.getCharacter();

        Assert.assertSame(randomFileBaseName, character.getFileBaseName());
        Assert.assertEquals(initialMapPositionX, character.getMapPositionX());
        Assert.assertEquals(initialMapPositionY, character.getMapPositionY());
    }
}

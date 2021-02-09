package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class PlayerCharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private Random random;

    public PlayerCharacterCreatorTest() {
        random = new Random();
    }

    public void testCreatePlayerCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreatePlayerCharacter();
        }
    }

    private void checkCreatePlayerCharacter() {
        GameController gameController = new GameControllerSpy();
        String randomFileBaseName = RandomGenerator.getRandomString();
        int initialMapPositionX = random.nextInt(100) + 2;
        int initialMapPositionY = random.nextInt(100) + 2;
        PlayerCharacterCreator playerCharacterCreator = (
            new PlayerCharacterCreator(
                gameController,
                randomFileBaseName,
                initialMapPositionX,
                initialMapPositionY
            )
        );

        playerCharacterCreator.execute();

        CharacterElement playerCharacterElement = (
            gameController.getPlayerCharacterElement()
        );
        GameCharacter playerCharacter = playerCharacterElement.getCharacter();

        Assert.assertSame(
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
    }
}

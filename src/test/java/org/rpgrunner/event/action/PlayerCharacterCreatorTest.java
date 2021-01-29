package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.event.action.PlayerCharacterCreatorTest;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;

public class PlayerCharacterCreatorTest extends TestCase {
    private Random random;

    public PlayerCharacterCreatorTest() {
        random = new Random();
    }

    public void testCreatePlayerCharacter() {
        GameController gameController;
        String randomFileBaseName;
        int initialMapPositionX;
        int initialMapPositionY;
        PlayerCharacterCreator playerCharacterCreator;

        for (int i = 0; i < 100; i++) {
            gameController = new GameControllerSpy();
            randomFileBaseName = RandomGenerator.getRandomString();
            initialMapPositionX = random.nextInt(100) + 2;
            initialMapPositionY = random.nextInt(100) + 2;
            playerCharacterCreator = new PlayerCharacterCreator(
                gameController,
                randomFileBaseName,
                initialMapPositionX,
                initialMapPositionY
            );

            playerCharacterCreator.execute();

            CharacterElement playerCharacterElement = (
                gameController.getPlayerCharacterElement()
            );
            GameCharacter playerCharacter = (
                playerCharacterElement.getCharacter()
            );

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
}

package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;

public class PlayerCharacterCreatorTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MINIMUM_INITIAL_POSITION = 2;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final PlayerMovementFactory playerMovementFactory;

    public PlayerCharacterCreatorTest() {
        characterAnimationFactory = new CharacterAnimationFactoryMock();
        playerMovementFactory = new PlayerMovementFactoryMock();
    }

    public void testCreatePlayerCharacterLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCreatePlayerCharacter();
        }
    }

    private void checkCreatePlayerCharacter() {
        GameController gameController = new GameControllerSpy();
        String randomFileBaseName = RandomGenerator.getRandomString();
        int initialMapPositionX = (
            RandomGenerator.getRandomPosition()
            + MINIMUM_INITIAL_POSITION
        );
        int initialMapPositionY = (
            RandomGenerator.getRandomPosition()
            + MINIMUM_INITIAL_POSITION
        );
        PlayerCharacterCreator playerCharacterCreator = (
            new PlayerCharacterCreator(
                gameController,
                characterAnimationFactory,
                playerMovementFactory,
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

package org.rpgrunner;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;
import org.rpgrunner.test.mock.character.movement.MovementSpy;
import org.rpgrunner.test.mock.character.movement.PlayerMovementSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;
import org.rpgrunner.test.mock.helper.CameraSpy;

public class GameControllerTest extends TestCase {
    private static final int MAXIMUM_KEY_VALUE = 100;
    private final Random random;
    private GameController gameController;
    private MapController mapController;
    private GraphicsRenderSpy graphicsRender;
    private CameraSpy camera;
    private CharacterElement playerCharacterElement;
    private PlayerMovementSpy playerMovementSpy;
    private Vector npcs;

    public GameControllerTest() {
        random = new Random();
    }

    public void setUp() {
        graphicsRender = new GraphicsRenderSpy();
        camera = new CameraSpy();
        playerMovementSpy = new PlayerMovementSpy();
        CharacterAnimation characterAnimation = new CharacterAnimationSpy();
        playerCharacterElement = new CharacterElement(
            null,
            new SimpleCharacter(),
            characterAnimation,
            playerMovementSpy
        );
        gameController = new GameController(graphicsRender, camera);
        mapController = gameController.getMapController();
        generateNPCs();
    }

    private void generateNPCs() {
        npcs = RandomGenerator.generateRandomCharacterElements();

        for (
            Enumeration enumeration = npcs.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement npc = (CharacterElement) enumeration.nextElement();
            mapController.addCharacterElement(npc);
        }
    }

    public void testPressKeyOnPlayerCharacterMovement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, playerMovementSpy.getPressedKey());
    }

    public void testReleaseKeyOnPlayerCharacterMovement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, playerMovementSpy.getReleasedKey());
    }

    public void testPrepareFrameAnimation() {
        gameController.prepareFrameAnimation();

        for (
            Enumeration enumeration = npcs.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement npc = (CharacterElement) enumeration.nextElement();
            MovementSpy movementSpy = (MovementSpy) npc.getMovementCommand();
            CharacterAnimationSpy characterAnimationSpy = (
                (CharacterAnimationSpy) npc.getCharacterAnimation()
            );
            Assert.assertTrue(movementSpy.isExecuteCalled());
            Assert.assertTrue(characterAnimationSpy.isDoAnimationCalled());
        }
    }

    public void testRender() {
        gameController.render();

        Assert.assertTrue(graphicsRender.isRenderCalled());
    }

    private CharacterElement generatePlayerCharacterElement() {
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterAnimationSpy characterAnimationSpy = (
            new CharacterAnimationSpy()
        );
        PlayerMovementSpy newPlayerMovementSpy = new PlayerMovementSpy();

        return new CharacterElement(
            null,
            character,
            characterAnimationSpy,
            newPlayerMovementSpy
        );
    }

    public void testExecuteAction() {
        ActionSpy action = new ActionSpy();
        gameController.executeAction(action);

        Assert.assertTrue(action.isExecuteCalled());
    }

    public void testSameMessagePassed() {
        String message = RandomGenerator.getRandomString();
        gameController.showMessage(message);

        Assert.assertSame(message, graphicsRender.getLastMessage());
    }
}

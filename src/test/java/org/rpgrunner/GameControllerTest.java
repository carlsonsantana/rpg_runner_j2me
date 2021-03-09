package org.rpgrunner;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;
import org.rpgrunner.test.mock.character.movement.MovementSpy;
import org.rpgrunner.test.mock.character.movement.PlayerMovementSpy;
import org.rpgrunner.test.mock.event.GameStartEventSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;
import org.rpgrunner.test.mock.helper.CameraSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class GameControllerTest extends TestCase {
    private static final int MAXIMUM_KEY_VALUE = 100;
    private final Random random;
    private GameController gameController;
    private GraphicsRenderSpy graphicsRender;
    private CameraSpy camera;
    private CharacterElement playerCharacterElement;
    private PlayerMovementSpy playerMovementSpy;
    private CharacterAnimationSpy characterAnimation;
    private Vector npcs;

    public GameControllerTest() {
        random = new Random();
    }

    public void setUp() {
        graphicsRender = new GraphicsRenderSpy();
        camera = new CameraSpy();
        playerMovementSpy = new PlayerMovementSpy();
        characterAnimation = new CharacterAnimationSpy();
        playerCharacterElement = new CharacterElement(
            null,
            new SimpleCharacter(),
            characterAnimation,
            playerMovementSpy
        );
        gameController = new GameController(graphicsRender, camera);
        generateNPCs();
    }

    private void generateNPCs() {
        npcs = RandomGenerator.generateRandomCharacterElements();

        for (
            Enumeration enumeration = npcs.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement npc = (CharacterElement) enumeration.nextElement();
            gameController.addCharacterElement(npc);
        }
    }

    public void testExecuteStartActions() {
        GameStartEventSpy gameStartEvent = new GameStartEventSpy();
        ActionAbstractFactorySpy actionAbstractFactory = (
            new ActionAbstractFactorySpy()
        );
        gameController.executeStartActions(
            actionAbstractFactory,
            gameStartEvent
        );

        Assert.assertTrue(gameStartEvent.isExecuteCalled());
    }

    public void testSameMapSetted() {
        MapSpy map = new MapSpy();
        gameController.setMap(map);

        Assert.assertSame(map, gameController.getMap());
        Assert.assertSame(map, graphicsRender.getMap());
        Assert.assertSame(map, camera.getMap());
    }

    public void testCallStartActionsWhenMapChange() {
        MapSpy map = new MapSpy();
        ActionSpy action = (ActionSpy) map.getStartAction();
        gameController.setMap(map);

        Assert.assertTrue(action.isExecuteCalled());
    }

    public void testPressKeyOnPlayerCharacterMovement() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, playerMovementSpy.getPressedKey());
    }

    public void testPressKeyWhenChangePlayerCharacterElement() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        CharacterElement newPlayerCharacterElement = (
            generatePlayerCharacterElement()
        );
        gameController.setPlayerCharacterElement(newPlayerCharacterElement);
        PlayerMovementSpy newPlayerMovementSpy = (
            (PlayerMovementSpy) newPlayerCharacterElement.getMovementCommand()
        );
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, newPlayerMovementSpy.getPressedKey());
    }

    public void testReleaseKeyOnPlayerCharacterMovement() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, playerMovementSpy.getReleasedKey());
    }

    public void testReleaseKeyWhenChangePlayerCharacterElement() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        CharacterElement newPlayerCharacterElement = (
            generatePlayerCharacterElement()
        );
        gameController.setPlayerCharacterElement(newPlayerCharacterElement);
        PlayerMovementSpy newPlayerMovementSpy = (
            (PlayerMovementSpy) newPlayerCharacterElement.getMovementCommand()
        );
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, newPlayerMovementSpy.getReleasedKey());
    }

    public void testExecuteCharacterActions() {
        gameController.setPlayerCharacterElement(playerCharacterElement);

        gameController.executeCharacterActions();

        Assert.assertTrue(playerMovementSpy.isExecuteCalled());
        Assert.assertTrue(characterAnimation.isDoAnimationCalled());

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

    public void testSamePlayerCharacterElement() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        Assert.assertSame(
            playerCharacterElement,
            gameController.getPlayerCharacterElement()
        );
        Assert.assertSame(characterAnimation, camera.getCharacterAnimation());
    }

    public void testChangePlayerCharacterElement() {
        CharacterElement newPlayerCharacterElement = (
            generatePlayerCharacterElement()
        );
        gameController.setPlayerCharacterElement(newPlayerCharacterElement);

        Assert.assertSame(
            newPlayerCharacterElement,
            gameController.getPlayerCharacterElement()
        );
        Assert.assertSame(
            newPlayerCharacterElement.getCharacterAnimation(),
            camera.getCharacterAnimation()
        );
    }

    public void testAddSameCharacterElementsOnGraphicsRender() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        Assert.assertTrue(
            characterElementsGraphics.contains(playerCharacterElement)
        );

        for (
            Enumeration enumeration = npcs.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement npc = (CharacterElement) enumeration.nextElement();
            Assert.assertTrue(characterElementsGraphics.contains(npc));
        }
    }

    public void testRemoveLastPlayerCharacterWhenChangePlayerCharacter() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        gameController.setPlayerCharacterElement(
            generatePlayerCharacterElement()
        );
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        Assert.assertFalse(
            characterElementsGraphics.contains(playerCharacterElement)
        );
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

    public void testRemoveAllNPCsWhenChangeMap() {
        MapSpy map = new MapSpy();
        gameController.setMap(map);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        for (
            Enumeration enumeration = npcs.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement npc = (CharacterElement) enumeration.nextElement();
            Assert.assertFalse(characterElementsGraphics.contains(npc));
        }
    }

    public void testKeepPlayerCharacterWhenChangeMap() {
        gameController.setPlayerCharacterElement(playerCharacterElement);
        MapSpy map = new MapSpy();
        gameController.setMap(map);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        Assert.assertTrue(
            characterElementsGraphics.contains(playerCharacterElement)
        );
    }

    public void testNotAddNullOnCharacterElementsWhenChangeMap() {
        MapSpy map = new MapSpy();
        gameController.setMap(map);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        Assert.assertFalse(characterElementsGraphics.contains(null));
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

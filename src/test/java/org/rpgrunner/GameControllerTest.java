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
        gameController.setPlayerCharacterElement(playerCharacterElement);
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
        gameController.setMap(map);

        Assert.assertTrue(map.isStartActionsCalled());
    }

    public void testPressKeyOnPlayerCharacterMovement() {
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, playerMovementSpy.getPressedKey());
    }

    public void testPressKeyWhenChangePlayerCharacterElement() {
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
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        gameController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, playerMovementSpy.getReleasedKey());
    }

    public void testReleaseKeyWhenChangePlayerCharacterElement() {
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
        Vector characterElements = (
            RandomGenerator.generateRandomCharacterElements()
        );
        addCharacterElements(characterElements);

        gameController.executeCharacterActions();

        Assert.assertTrue(playerMovementSpy.isExecuteCalled());
        Assert.assertTrue(characterAnimation.isDoAnimationCalled());

        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement characterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            MovementSpy movementSpy = (
                (MovementSpy) characterElement.getMovementCommand()
            );
            CharacterAnimationSpy characterAnimationSpy = (
                (CharacterAnimationSpy) characterElement.getCharacterAnimation()
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

    public void testAddSameCharacterElementsOnGraphicsRender() {
        Vector characterElements = (
            RandomGenerator.generateRandomCharacterElements()
        );
        addCharacterElements(characterElements);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement characterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            Assert.assertTrue(
                characterElementsGraphics.contains(characterElement)
            );
        }
    }

    private void addCharacterElements(final Vector characterElements) {
        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement characterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            gameController.addCharacterElement(characterElement);
        }
    }
}

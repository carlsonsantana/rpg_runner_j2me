package org.rpgrunner.controller;

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
import org.rpgrunner.test.mock.event.action.ActionSpy;
import org.rpgrunner.test.mock.graphics.GraphicsRenderSpy;
import org.rpgrunner.test.mock.helper.CameraSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class MapControllerTest extends TestCase {
    private static final int MAXIMUM_KEY_VALUE = 100;
    private final Random random;
    private MapController mapController;
    private GraphicsRenderSpy graphicsRender;
    private CameraSpy camera;
    private CharacterElement playerCharacterElement;
    private PlayerMovementSpy playerMovementSpy;
    private CharacterAnimationSpy characterAnimation;
    private Vector npcs;

    public MapControllerTest() {
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
        GameController gameController = new GameController(
            graphicsRender,
            camera
        );
        mapController = new MapController(
            gameController,
            graphicsRender,
            camera
        );
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

    public void testSameMapSetted() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);

        Assert.assertSame(map, mapController.getMap());
        Assert.assertSame(map, graphicsRender.getMap());
        Assert.assertSame(map, camera.getMap());
    }

    public void testCallStartActionsWhenMapChange() {
        MapSpy map = new MapSpy();
        ActionSpy action = (ActionSpy) map.getStartAction();
        mapController.setMap(map);

        Assert.assertTrue(action.isExecuteCalled());
    }

    public void testPressKeyOnPlayerCharacterMovement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        mapController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, playerMovementSpy.getPressedKey());
    }

    public void testPressKeyWhenChangePlayerCharacterElement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        CharacterElement newPlayerCharacterElement = (
            generatePlayerCharacterElement()
        );
        mapController.setPlayerCharacterElement(newPlayerCharacterElement);
        PlayerMovementSpy newPlayerMovementSpy = (
            (PlayerMovementSpy) newPlayerCharacterElement.getMovementCommand()
        );
        int keyPressed = random.nextInt(MAXIMUM_KEY_VALUE);
        mapController.pressKey(keyPressed);

        Assert.assertEquals(keyPressed, newPlayerMovementSpy.getPressedKey());
    }

    public void testReleaseKeyOnPlayerCharacterMovement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        mapController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, playerMovementSpy.getReleasedKey());
    }

    public void testReleaseKeyWhenChangePlayerCharacterElement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        CharacterElement newPlayerCharacterElement = (
            generatePlayerCharacterElement()
        );
        mapController.setPlayerCharacterElement(newPlayerCharacterElement);
        PlayerMovementSpy newPlayerMovementSpy = (
            (PlayerMovementSpy) newPlayerCharacterElement.getMovementCommand()
        );
        int keyReleased = random.nextInt(MAXIMUM_KEY_VALUE);
        mapController.releaseKey(keyReleased);

        Assert.assertEquals(keyReleased, newPlayerMovementSpy.getReleasedKey());
    }

    public void testPrepareFrameAnimation() {
        mapController.setPlayerCharacterElement(playerCharacterElement);

        mapController.prepareFrameAnimation();

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

    public void testSamePlayerCharacterElement() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
        Assert.assertSame(
            playerCharacterElement,
            mapController.getPlayerCharacterElement()
        );
        Assert.assertSame(characterAnimation, camera.getCharacterAnimation());
    }

    public void testChangePlayerCharacterElement() {
        CharacterElement newPlayerCharacterElement = (
            generatePlayerCharacterElement()
        );
        mapController.setPlayerCharacterElement(newPlayerCharacterElement);

        Assert.assertSame(
            newPlayerCharacterElement,
            mapController.getPlayerCharacterElement()
        );
        Assert.assertSame(
            newPlayerCharacterElement.getCharacterAnimation(),
            camera.getCharacterAnimation()
        );
    }

    public void testAddSameCharacterElementsOnGraphicsRender() {
        mapController.setPlayerCharacterElement(playerCharacterElement);
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
        mapController.setPlayerCharacterElement(playerCharacterElement);
        mapController.setPlayerCharacterElement(
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
        mapController.setMap(map);
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
        mapController.setPlayerCharacterElement(playerCharacterElement);
        MapSpy map = new MapSpy();
        mapController.setMap(map);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        Assert.assertTrue(
            characterElementsGraphics.contains(playerCharacterElement)
        );
    }

    public void testNotAddNullOnCharacterElementsWhenChangeMap() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);
        Vector characterElementsGraphics = (
            graphicsRender.getCharacterElements()
        );

        Assert.assertFalse(characterElementsGraphics.contains(null));
    }
}

package org.rpgrunner.controller;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;
import org.rpgrunner.test.mock.character.movement.MovementSpy;
import org.rpgrunner.test.mock.character.movement.PlayerMovementSpy;
import org.rpgrunner.test.mock.graphics.MapGraphicsRenderSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class MapControllerTest extends TestCase {
    private static final int MAXIMUM_KEY_VALUE = 100;
    private static final int MAX_CHARACTERS_ELEMENTS = 200;
    private final Random random;
    private MapController mapController;
    private MapGraphicsRenderSpy mapGraphicsRender;
    private MapHelperSpy mapHelper;
    private CharacterElement playerCharacterElement;
    private PlayerMovementSpy playerMovementSpy;
    private CharacterAnimationSpy characterAnimation;
    private CharacterElement[] npcs;
    private CharacterElement[] characterElements;

    public MapControllerTest() {
        random = new Random();
    }

    public void setUp() {
        mapGraphicsRender = new MapGraphicsRenderSpy();
        playerMovementSpy = new PlayerMovementSpy();
        characterAnimation = new CharacterAnimationSpy();
        playerCharacterElement = new CharacterElement(
            new SimpleCharacter(),
            characterAnimation,
            playerMovementSpy
        );
        mapHelper = new MapHelperSpy();
        characterElements = new CharacterElement[MAX_CHARACTERS_ELEMENTS];
        mapController = new MapController(
            mapGraphicsRender,
            mapHelper,
            characterElements
        );
        mapController.setPlayerCharacterElement(playerCharacterElement);
        generateNPCs();
    }

    private void generateNPCs() {
        npcs = RandomGenerator.generateRandomCharacterElements();

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            CharacterElement npc = npcs[i];
            mapController.addCharacterElement(npc);
        }
    }

    public void testSameMapSetted() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);

        Assert.assertSame(map, mapController.getMap());
        Assert.assertSame(map, mapGraphicsRender.getMap());
        Assert.assertSame(map, mapHelper.getCurrentMap());
    }

    public void testPrepareFrameAnimation() {
        mapController.prepareFrameAnimation();

        Assert.assertTrue(playerMovementSpy.isExecuteCalled());
        Assert.assertTrue(characterAnimation.isDoAnimationCalled());

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            CharacterElement npc = npcs[i];
            MovementSpy movementSpy = (MovementSpy) npc.getMovementCommand();
            CharacterAnimationSpy characterAnimationSpy = (
                (CharacterAnimationSpy) npc.getCharacterAnimation()
            );
            Assert.assertTrue(movementSpy.isExecuteCalled());
            Assert.assertTrue(characterAnimationSpy.isDoAnimationCalled());
        }
    }

    public void testSamePlayerCharacterElement() {
        Assert.assertSame(
            playerCharacterElement,
            mapController.getPlayerCharacterElement()
        );
        Assert.assertSame(
            characterAnimation,
            mapGraphicsRender.getCharacterAnimation()
        );
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
            mapGraphicsRender.getCharacterAnimation()
        );
    }

    public void testAddSameCharacterElementsOnGraphicsRender() {
        Assert.assertTrue(containsOnCharacterElements(playerCharacterElement));

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            CharacterElement npc = npcs[i];
            Assert.assertTrue(containsOnCharacterElements(npc));
        }
    }

    public void testRemoveLastPlayerCharacterWhenChangePlayerCharacter() {
        mapController.setPlayerCharacterElement(
            generatePlayerCharacterElement()
        );

        Assert.assertFalse(containsOnCharacterElements(playerCharacterElement));
    }

    private CharacterElement generatePlayerCharacterElement() {
        GameCharacter character = RandomGenerator.generateRandomCharacter();
        CharacterAnimationSpy characterAnimationSpy = (
            new CharacterAnimationSpy()
        );
        PlayerMovementSpy newPlayerMovementSpy = new PlayerMovementSpy();

        return new CharacterElement(
            character,
            characterAnimationSpy,
            newPlayerMovementSpy
        );
    }

    public void testRemoveAllNPCsWhenChangeMap() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            CharacterElement npc = npcs[i];
            Assert.assertFalse(containsOnCharacterElements(npc));
        }
    }

    public void testKeepPlayerCharacterWhenChangeMap() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);

        Assert.assertTrue(containsOnCharacterElements(playerCharacterElement));
    }

    public void testCallGraphicsRender() {
        Assert.assertFalse(mapGraphicsRender.isRenderCalled());

        mapController.render();

        Assert.assertTrue(mapGraphicsRender.isRenderCalled());
    }

    private boolean containsOnCharacterElements(
        final CharacterElement characterElement
    ) {
        for (int i = 0; i < MAX_CHARACTERS_ELEMENTS; i++) {
            if (characterElements[i] == characterElement) {
                return true;
            }
        }

        return false;
    }
}

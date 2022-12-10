package org.rpgrunner.controller;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
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
    private CharacterSpy playerCharacter;
    private PlayerMovementSpy playerMovementSpy;
    private GameCharacter[] npcs;
    private GameCharacter[] characters;

    public MapControllerTest() {
        random = new Random();
    }

    public void setUp() {
        mapGraphicsRender = new MapGraphicsRenderSpy();
        playerMovementSpy = new PlayerMovementSpy();
        playerCharacter = new CharacterSpy();
        playerCharacter.setMovementCommand(playerMovementSpy);
        mapHelper = new MapHelperSpy();
        characters = new GameCharacter[MAX_CHARACTERS_ELEMENTS];
        mapController = new MapController(
            mapGraphicsRender,
            mapHelper,
            characters
        );
        mapController.setPlayerCharacter(playerCharacter);
        generateNPCs();
    }

    private void generateNPCs() {
        npcs = RandomGenerator.generateRandomCharacters();

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            GameCharacter npc = npcs[i];
            mapController.addCharacter(npc);
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
        Assert.assertTrue(playerCharacter.isDoAnimationCalled());

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            CharacterSpy npc = (CharacterSpy) npcs[i];
            MovementSpy movementSpy = (MovementSpy) npc.getMovementCommand();
            Assert.assertTrue(movementSpy.isExecuteCalled());
            Assert.assertTrue(npc.isDoAnimationCalled());
        }
    }

    public void testSamePlayerCharacter() {
        Assert.assertSame(playerCharacter, mapController.getPlayerCharacter());
        Assert.assertSame(playerCharacter, mapGraphicsRender.getCharacter());
    }

    public void testChangePlayerCharacter() {
        GameCharacter newPlayerCharacter = generatePlayerCharacter();
        mapController.setPlayerCharacter(newPlayerCharacter);

        Assert.assertSame(
            newPlayerCharacter,
            mapController.getPlayerCharacter()
        );
        Assert.assertSame(newPlayerCharacter, mapGraphicsRender.getCharacter());
    }

    public void testAddSameCharactersOnGraphicsRender() {
        Assert.assertTrue(containsOnCharacters(playerCharacter));

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            GameCharacter npc = npcs[i];
            Assert.assertTrue(containsOnCharacters(npc));
        }
    }

    public void testRemoveLastPlayerCharacterWhenChangePlayerCharacter() {
        mapController.setPlayerCharacter(generatePlayerCharacter());

        Assert.assertFalse(containsOnCharacters(playerCharacter));
    }

    private GameCharacter generatePlayerCharacter() {
        PlayerMovementSpy newPlayerMovementSpy = new PlayerMovementSpy();
        CharacterSpy characterSpy = new CharacterSpy();
        characterSpy.setMovementCommand(newPlayerMovementSpy);

        return characterSpy;
    }

    public void testRemoveAllNPCsWhenChangeMap() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);

        for (
            int i = 0, length = npcs.length;
            (i < length) && (npcs[i] != null);
            i++
        ) {
            GameCharacter npc = npcs[i];
            Assert.assertFalse(containsOnCharacters(npc));
        }
    }

    public void testKeepPlayerCharacterWhenChangeMap() {
        MapSpy map = new MapSpy();
        mapController.setMap(map);

        Assert.assertTrue(containsOnCharacters(playerCharacter));
    }

    public void testCallGraphicsRender() {
        Assert.assertFalse(mapGraphicsRender.isRenderCalled());

        mapController.render();

        Assert.assertTrue(mapGraphicsRender.isRenderCalled());
    }

    private boolean containsOnCharacters(final GameCharacter character) {
        for (int i = 0; i < MAX_CHARACTERS_ELEMENTS; i++) {
            if (characters[i] == character) {
                return true;
            }
        }

        return false;
    }
}

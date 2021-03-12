package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.MapControllerSpy;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;

public abstract class AbstractTeleportTest extends TestCase {
    private static final int EXAMPLE_MAP_WIDTH = 32;
    private static final int EXAMPLE_MAP_HEIGHT = 32;
    private static final int ANOTHER_MAP_WIDTH = 16;
    private static final int ANOTHER_MAP_HEIGHT = 16;
    private CharacterElement characterElement;
    private GameCharacter character;
    private GameControllerSpy gameController;
    private MapControllerSpy mapController;
    private Teleport teleport;
    private int mapPositionX;
    private int mapPositionY;

    public void setUp() {
        character = new SimpleCharacter();
        CharacterAnimationSpy characterAnimation = new CharacterAnimationSpy();
        characterElement = new CharacterElement(
            null,
            character,
            characterAnimation,
            null
        );
        gameController = new GameControllerSpy();
        mapController = (MapControllerSpy) gameController.getMapController();
        mapController.setPlayerCharacterElement(characterElement);
        mapPositionX = RandomGenerator.getRandomPosition();
        mapPositionY = RandomGenerator.getRandomPosition();
        teleport = createTeleport(
            gameController,
            "example",
            mapPositionX,
            mapPositionY
        );
    }

    public void testChangeMap() {
        testTeleport(
            teleport,
            EXAMPLE_MAP_WIDTH,
            EXAMPLE_MAP_HEIGHT,
            mapPositionX,
            mapPositionY
        );
    }

    public void testChangeMapTwice() {
        int nextMapPositionX = RandomGenerator.getRandomPosition();
        int nextMapPositionY = RandomGenerator.getRandomPosition();
        Teleport teleportAnotherMap = createTeleport(
            gameController,
            "another",
            nextMapPositionX,
            nextMapPositionY
        );

        teleport.execute();

        testTeleport(
            teleportAnotherMap,
            ANOTHER_MAP_WIDTH,
            ANOTHER_MAP_HEIGHT,
            nextMapPositionX,
            nextMapPositionY
        );
        Assert.assertEquals(2, mapController.getCountMapChanged());
    }

    public void testChangeTeleportSameMap() {
        int nextMapPositionX = RandomGenerator.getRandomPosition();
        int nextMapPositionY = RandomGenerator.getRandomPosition();
        Teleport teleportSameMap = createTeleport(
            gameController,
            "example",
            nextMapPositionX,
            nextMapPositionY
        );

        teleport.execute();

        testTeleport(
            teleportSameMap,
            EXAMPLE_MAP_WIDTH,
            EXAMPLE_MAP_HEIGHT,
            nextMapPositionX,
            nextMapPositionY
        );
        Assert.assertEquals(2, mapController.getCountMapChanged());
    }

    private void testTeleport(
        final Teleport teleportToExecute,
        final int currentMapHeight,
        final int currentMapWidth,
        final int characterMapPositionX,
        final int characterMapPositionY
    ) {
        teleportToExecute.execute();

        Map map = mapController.getMap();
        Assert.assertEquals(currentMapHeight, map.getHeight());
        Assert.assertEquals(currentMapWidth, map.getWidth());
        Assert.assertEquals(characterMapPositionX, character.getMapPositionX());
        Assert.assertEquals(characterMapPositionY, character.getMapPositionY());
        Assert.assertEquals(
            characterMapPositionX,
            character.getMapNextPositionX()
        );
        Assert.assertEquals(
            characterMapPositionY,
            character.getMapNextPositionY()
        );
    }

    protected abstract Teleport createTeleport(
        GameController gameControllerTest,
        String mapFileName,
        int newMapPositionX,
        int newMapPositionY
    );
}

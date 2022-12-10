package org.rpgrunner.event.action;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.controller.MapControllerSpy;
import org.rpgrunner.test.mock.event.ActionQueueSpy;

public abstract class AbstractTeleportTest extends TestCase {
    private static final int EXAMPLE_MAP_WIDTH = 32;
    private static final int EXAMPLE_MAP_HEIGHT = 32;
    private static final int ANOTHER_MAP_WIDTH = 16;
    private static final int ANOTHER_MAP_HEIGHT = 16;
    private static final byte FIRST_MAP_ID = (byte) 0;
    private static final byte SECOND_MAP_ID = (byte) 1;
    private GameCharacter character;
    private MapControllerSpy mapController;
    private ActionQueueSpy actionQueue;
    private Teleport teleport;
    private int mapPositionX;
    private int mapPositionY;

    public void setUp() {
        character = new CharacterSpy();
        mapController = new MapControllerSpy();
        mapController.setPlayerCharacter(character);
        actionQueue = new ActionQueueSpy();
        mapPositionX = RandomGenerator.getRandomPosition();
        mapPositionY = RandomGenerator.getRandomPosition();
        teleport = createTeleport(
            mapController,
            actionQueue,
            FIRST_MAP_ID,
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
        Assert.assertEquals(1, actionQueue.getActions().length);
    }

    public void testChangeMapTwice() {
        int nextMapPositionX = RandomGenerator.getRandomPosition();
        int nextMapPositionY = RandomGenerator.getRandomPosition();
        Teleport teleportAnotherMap = createTeleport(
            mapController,
            actionQueue,
            SECOND_MAP_ID,
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
        Assert.assertEquals(2, actionQueue.getActions().length);
    }

    public void testChangeTeleportSameMap() {
        int nextMapPositionX = RandomGenerator.getRandomPosition();
        int nextMapPositionY = RandomGenerator.getRandomPosition();
        Teleport teleportSameMap = createTeleport(
            mapController,
            actionQueue,
            FIRST_MAP_ID,
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
        Assert.assertEquals(2, actionQueue.getActions().length);
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
        MapController mapControllerTest,
        ActionQueue actionQueueTest,
        byte mapID,
        int newMapPositionX,
        int newMapPositionY
    );
}

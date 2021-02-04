package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.character.SimpleCharacter;

public class TeleportTest extends TestCase {
    private final Random random;
    private CharacterElement characterElement;
    private GameCharacter character;
    private GameControllerSpy gameController;
    private Teleport teleport;
    private int mapPositionX;
    private int mapPositionY;

    public TeleportTest() {
        random = new Random();
    }

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
        gameController.setPlayerCharacterElement(characterElement);
        mapPositionX = random.nextInt(100);
        mapPositionY = random.nextInt(100);
        teleport = new Teleport(
            gameController,
            "example",
            mapPositionX,
            mapPositionY
        );
    }

    public void testChangeMap() {
        testTeleport(teleport, 32, 32, mapPositionX, mapPositionY);
    }

    public void testChangeMapTwice() {
        int nextMapPositionX = random.nextInt(100);
        int nextMapPositionY = random.nextInt(100);
        Teleport teleportAnotherMap = new Teleport(
            gameController,
            "another",
            nextMapPositionX,
            nextMapPositionY
        );

        teleport.execute();

        testTeleport(
            teleportAnotherMap,
            16,
            16,
            nextMapPositionX,
            nextMapPositionY
        );
        Assert.assertEquals(2, gameController.getCountMapChanged());
    }

    public void testChangeTeleportSameMap() {
        int nextMapPositionX = random.nextInt(100);
        int nextMapPositionY = random.nextInt(100);
        Teleport teleportSameMap = new Teleport(
            gameController,
            "example",
            nextMapPositionX,
            nextMapPositionY
        );

        teleport.execute();

        testTeleport(
            teleportSameMap,
            32,
            32,
            nextMapPositionX,
            nextMapPositionY
        );
        Assert.assertEquals(2, gameController.getCountMapChanged());
    }

    private void testTeleport(
        final Teleport teleportToExecute,
        final int currentMapHeight,
        final int currentMapWidth,
        final int characterMapPositionX,
        final int characterMapPositionY
    ) {
        teleportToExecute.execute();

        Map map = gameController.getMap();
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
}

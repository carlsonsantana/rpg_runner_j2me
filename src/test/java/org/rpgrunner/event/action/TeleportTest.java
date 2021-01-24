package org.rpgrunner.event.action;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.SimpleCharacter;

public class TeleportTest extends TestCase {
    private final Random random;
    private CharacterElement characterElement;
    private GameCharacter character;
    private GameControllerSpy gameController;
    private Teleport event;
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
        event = new Teleport(
            gameController,
            "example",
            mapPositionX,
            mapPositionY
        );
    }

    public void testChangeMap() {
        event.execute();

        Map map = gameController.getMap();
        Assert.assertEquals(32, map.getHeight());
        Assert.assertEquals(32, map.getWidth());
        Assert.assertEquals(mapPositionX, character.getMapPositionX());
        Assert.assertEquals(mapPositionY, character.getMapPositionY());
        Assert.assertEquals(mapPositionX, character.getMapNextPositionX());
        Assert.assertEquals(mapPositionY, character.getMapNextPositionY());
    }

    public void testChangeMapTwice() {
        int nextMapPositionX = random.nextInt(100);
        int nextMapPositionY = random.nextInt(100);
        Teleport eventTeleportAnotherMap = new Teleport(
            gameController,
            "another",
            nextMapPositionX,
            nextMapPositionY
        );

        event.execute();
        eventTeleportAnotherMap.execute();

        Map map = gameController.getMap();
        Assert.assertEquals(16, map.getHeight());
        Assert.assertEquals(16, map.getWidth());
        Assert.assertEquals(2, gameController.getCountMapChanged());
        Assert.assertEquals(nextMapPositionX, character.getMapPositionX());
        Assert.assertEquals(nextMapPositionY, character.getMapPositionY());
        Assert.assertEquals(nextMapPositionX, character.getMapNextPositionX());
        Assert.assertEquals(nextMapPositionY, character.getMapNextPositionY());
    }

    public void testNotChangeTeleportSameMap() {
        int nextMapPositionX = random.nextInt(100);
        int nextMapPositionY = random.nextInt(100);
        Teleport eventTeleportSameMap = new Teleport(
            gameController,
            "example",
            nextMapPositionX,
            nextMapPositionY
        );

        event.execute();
        eventTeleportSameMap.execute();

        Map map = gameController.getMap();
        Assert.assertEquals(1, gameController.getCountMapChanged());
        Assert.assertEquals(nextMapPositionX, character.getMapPositionX());
        Assert.assertEquals(nextMapPositionY, character.getMapPositionY());
        Assert.assertEquals(nextMapPositionX, character.getMapNextPositionX());
        Assert.assertEquals(nextMapPositionY, character.getMapNextPositionY());
    }
}

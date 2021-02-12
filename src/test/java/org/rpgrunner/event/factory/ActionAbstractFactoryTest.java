package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.GameControllerSpy;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class ActionAbstractFactoryTest extends TestCase {
    private static final byte ACTION_LIST_FACTORY = (byte) 0;
    private static final byte PLAYER_CHARACTER_CREATOR_FACTORY = (byte) 1;
    private static final byte CHARACTER_CREATOR_FACTORY = (byte) 2;
    private static final byte TELEPORT_FACTORY = (byte) 3;
    private static final byte LOCAL_TELEPORT_FACTORY = (byte) 4;
    private static final int ADDITIONAL_BYTES = 4;
    private static final int BYTES_LOCAL_TELEPORT = 3;
    private static final int ANOTHER_MAP_WIDTH = 16;
    private static final int ANOTHER_MAP_HEIGHT = 16;
    private ActionAbstractFactory actionAbstractFactory;
    private GameControllerSpy gameController;
    private CharacterElement characterElement;
    private int mapPositionX;
    private int mapPositionY;

    public ActionAbstractFactoryTest() {
        gameController = new GameControllerSpy();
        actionAbstractFactory = new ActionAbstractFactory(gameController);
    }

    public void setUp() {
        characterElement = RandomGenerator.generateRandomCharacterElement();
        mapPositionX = RandomGenerator.getRandomPosition();
        mapPositionY = RandomGenerator.getRandomPosition();
    }

    public void testActionListFactory() throws IOException {
        byte[] byteArray = new byte[] {ACTION_LIST_FACTORY, 0};
        InputStream inputStream = new ByteArrayInputStream(byteArray);

        Action action = actionAbstractFactory.create(inputStream);

        ActionListFactoryTest.checkActionListFactory(
            action,
            new ActionAbstractFactorySpy(),
            0
        );
    }

    public void testPlayerCharacterCreatorFactory() throws IOException {
        String characterFileName = RandomGenerator.getRandomString();
        InputStream inputStream = getInputStream(
            characterFileName,
            PLAYER_CHARACTER_CREATOR_FACTORY
        );

        Action action = actionAbstractFactory.create(inputStream);
        PlayerCharacterCreatorFactoryTest playerCharacterCreatorFactoryTest = (
            new PlayerCharacterCreatorFactoryTest()
        );

        playerCharacterCreatorFactoryTest.checkCharacterCreatorFactory(
            action,
            gameController,
            characterFileName,
            mapPositionX,
            mapPositionY
        );
    }

    public void testCharacterCreatorFactory() throws IOException {
        String characterFileName = RandomGenerator.getRandomString();
        InputStream inputStream = getInputStream(
            characterFileName,
            CHARACTER_CREATOR_FACTORY
        );

        Action action = actionAbstractFactory.create(inputStream);
        CharacterCreatorFactoryTest characterCreatorFactoryTest = (
            new CharacterCreatorFactoryTest()
        );

        characterCreatorFactoryTest.checkCharacterCreatorFactory(
            action,
            gameController,
            characterFileName,
            mapPositionX,
            mapPositionY
        );
    }

    public void testTeleportFactory() throws IOException {
        String mapFileName = "another";

        gameController.setPlayerCharacterElement(characterElement);
        InputStream inputStream = getInputStream(mapFileName, TELEPORT_FACTORY);
        Action action = actionAbstractFactory.create(inputStream);

        TeleportFactoryTest.checkTeleportFactory(
            action,
            gameController,
            characterElement,
            ANOTHER_MAP_WIDTH,
            ANOTHER_MAP_HEIGHT,
            mapPositionX,
            mapPositionY
        );
    }

    private InputStream getInputStream(
        final String name,
        final byte actionFactory
    ) {
        int arraySize = InputStreamHelper.getStringLength(name);
        byte[] byteArray = new byte[arraySize + ADDITIONAL_BYTES];
        byteArray[0] = actionFactory;
        InputStreamHelper.setByteArray(byteArray, 1, name);
        InputStreamHelper.setPosition(
            byteArray,
            arraySize + 2,
            mapPositionX,
            mapPositionY
        );

        return new ByteArrayInputStream(byteArray);
    }

    public void testLocalTeleportFactory() throws IOException {
        InputStream inputStream = getInputStreamLocalTeleport();

        Action action = actionAbstractFactory.create(inputStream);

        LocalTeleportFactoryTest.checkLocalTeleportFactory(
            action,
            characterElement,
            mapPositionX,
            mapPositionY
        );
    }

    private InputStream getInputStreamLocalTeleport() {
        byte[] byteArray = new byte[BYTES_LOCAL_TELEPORT];
        byteArray[0] = LOCAL_TELEPORT_FACTORY;
        InputStreamHelper.setPosition(byteArray, 1, mapPositionX, mapPositionY);

        return new ByteArrayInputStream(byteArray);
    }
}

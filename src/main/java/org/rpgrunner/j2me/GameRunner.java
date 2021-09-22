package org.rpgrunner.j2me;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.controller.MessageController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.GameStartEvent;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.event.factory.ActionListFactory;
import org.rpgrunner.event.factory.CharacterCreatorFactory;
import org.rpgrunner.event.factory.LocalTeleportFactory;
import org.rpgrunner.event.factory.NullActionFactory;
import org.rpgrunner.event.factory.PlayerCharacterCreatorFactory;
import org.rpgrunner.event.factory.ShowMessageFactory;
import org.rpgrunner.event.factory.TeleportFactory;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.j2me.character.CharacterAnimationFactoryImpl;
import org.rpgrunner.j2me.character.movement.PlayerMovementFactoryImpl;
import org.rpgrunner.j2me.controller.MessageControllerImpl;
import org.rpgrunner.j2me.graphics.MapGraphicsRenderImpl;
import org.rpgrunner.j2me.graphics.MessageGraphicsRenderImpl;
import org.rpgrunner.j2me.helper.InputImpl;
import org.rpgrunner.map.MapLoader;

public class GameRunner extends GameCanvas {
    private static final int FRAMES_PER_SECOND = 100;
    private static final int[] ALLOWED_KEYS = new int[] {
        KEY_NUM0,
        KEY_NUM1,
        KEY_NUM2,
        KEY_NUM3,
        KEY_NUM4,
        KEY_NUM5,
        KEY_NUM6,
        KEY_NUM7,
        KEY_NUM8,
        KEY_NUM9,
        KEY_POUND,
        KEY_STAR
    };

    private final CharacterAnimationFactoryImpl characterAnimationFactory;
    private final InputImpl input;
    private boolean destroyed;
    private GameController gameController;
    private ActionQueue actionQueue;

    public GameRunner() {
        super(false);

        destroyed = false;
        characterAnimationFactory = new CharacterAnimationFactoryImpl();
        input = new InputImpl();
    }

    public void start() {
        configure();

        executeGame();
    }

    private void configure() {
        Graphics graphics = getGraphics();
        graphics.setFont(Font.getDefaultFont());
        int screenWidth = getWidth();
        int screenHeight = getHeight();
        actionQueue = new ActionQueue();
        MapHelper mapHelper = new MapHelper(actionQueue);
        MapGraphicsRenderImpl mapGraphicsRender = new MapGraphicsRenderImpl(
            graphics,
            screenWidth,
            screenHeight
        );
        MessageGraphicsRenderImpl messageGraphicsRender = (
            new MessageGraphicsRenderImpl(graphics, screenWidth, screenHeight)
        );
        MapController mapController = new MapController(
            mapGraphicsRender,
            mapHelper
        );
        MessageController messageController = new MessageControllerImpl(
            messageGraphicsRender,
            input
        );
        gameController = new GameController(mapController, messageController);
        ActionAbstractFactory actionAbstractFactory = (
            createActionAbstractFactory(mapController, mapHelper)
        );
        GameStartEvent gameStartEvent = new GameStartEvent();

        gameStartEvent.execute(actionAbstractFactory);
    }

    private ActionAbstractFactory createActionAbstractFactory(
        final MapController mapController,
        final MapHelper mapHelper
    ) {
        ActionAbstractFactory actionAbstractFactory = (
            new ActionAbstractFactory()
        );
        MapLoader mapLoader = new MapLoader(actionAbstractFactory);
        PlayerMovementFactory playerMovementFactory = (
            new PlayerMovementFactoryImpl(mapHelper)
        );

        NullActionFactory nullActionFactory = new NullActionFactory();
        ActionListFactory actionListFactory = new ActionListFactory(
            actionAbstractFactory
        );
        PlayerCharacterCreatorFactory playerCharacterCreatorFactory = (
            new PlayerCharacterCreatorFactory(
                mapController,
                characterAnimationFactory,
                playerMovementFactory
            )
        );
        CharacterCreatorFactory characterCreatorFactory = (
            new CharacterCreatorFactory(
                mapController,
                characterAnimationFactory,
                actionAbstractFactory
            )
        );
        TeleportFactory teleportFactory = new TeleportFactory(
            mapController,
            mapLoader,
            actionQueue
        );
        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();
        ShowMessageFactory showMessageFactory = new ShowMessageFactory(
            gameController
        );
        actionAbstractFactory.addActionFactory(nullActionFactory);
        actionAbstractFactory.addActionFactory(actionListFactory);
        actionAbstractFactory.addActionFactory(playerCharacterCreatorFactory);
        actionAbstractFactory.addActionFactory(characterCreatorFactory);
        actionAbstractFactory.addActionFactory(teleportFactory);
        actionAbstractFactory.addActionFactory(localTeleportFactory);
        actionAbstractFactory.addActionFactory(showMessageFactory);

        return actionAbstractFactory;
    }

    private void executeGame() {
        while (isRunning()) {
            executeFrame();
        }
    }

    private void executeFrame() {
        long startFrameTime = System.currentTimeMillis();
        gameController.prepareFrameAnimation();
        actionQueue.execute();
        renderFrame();
        waitUntilEndTimeFrame(startFrameTime);
    }

    private void renderFrame() {
        gameController.render();
        repaint();
        flushGraphics();
    }

    private void waitUntilEndTimeFrame(final long startFrameTime) {
        try {
            long currentTime = System.currentTimeMillis();
            long diffTime = currentTime - startFrameTime;
            long sleepTime = FRAMES_PER_SECOND - diffTime;

            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ie) { }
    }

    private boolean isRunning() {
        return !destroyed;
    }

    public void destroy() {
        destroyed = true;
    }

    protected void keyPressed(final int keyCode) {
        super.keyPressed(keyCode);

        if (isAllowedKey(keyCode)) {
            gameController.pressKey(keyCode);
            input.pressKey(keyCode);
        } else {
            int gameAction = getGameAction(keyCode);
            gameController.pressKey(gameAction);
            input.pressKey(gameAction);
        }
    }

    protected void keyReleased(final int keyCode) {
        super.keyReleased(keyCode);

        if (isAllowedKey(keyCode)) {
            gameController.releaseKey(keyCode);
            input.releaseKey(keyCode);
        } else {
            int gameAction = getGameAction(keyCode);
            gameController.releaseKey(gameAction);
            input.releaseKey(gameAction);
        }
    }

    private boolean isAllowedKey(final int keyCode) {
        for (int i = 0, lenght = ALLOWED_KEYS.length; i < lenght; i++) {
            int key = ALLOWED_KEYS[i];

            if (key == keyCode) {
                return true;
            }
        }

        return false;
    }
}

package org.rpgrunner.j2me;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.controller.MessageController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.GameStartEvent;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.j2me.character.CharacterAnimationFactoryImpl;
import org.rpgrunner.j2me.character.movement.PlayerMovementFactoryImpl;
import org.rpgrunner.j2me.controller.MessageControllerImpl;
import org.rpgrunner.j2me.graphics.GraphicsRenderImpl;
import org.rpgrunner.j2me.graphics.MapGraphicsRender;

public class GameRunner extends GameCanvas implements Runnable {
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
    private final PlayerMovementFactoryImpl playerMovementFactory;
    private Thread thread;
    private boolean destroyed;
    private GameController gameController;
    private ActionQueue actionQueue;

    public GameRunner() {
        super(false);

        destroyed = false;
        characterAnimationFactory = new CharacterAnimationFactoryImpl();
        playerMovementFactory = new PlayerMovementFactoryImpl();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        configure();

        executeGame();
    }

    private void configure() {
        Graphics graphics = getGraphics();
        graphics.setFont(Font.getDefaultFont());
        Camera camera = new Camera(getWidth(), getHeight());
        actionQueue = new ActionQueue();
        MapHelper mapHelper = new MapHelper(actionQueue);
        GraphicsRenderImpl graphicsRender = new GraphicsRenderImpl(
            graphics,
            camera
        );
        MapGraphicsRender mapGraphicsRender = new MapGraphicsRender(
            graphics,
            camera
        );
        MapController mapController = new MapController(
            mapGraphicsRender,
            camera,
            mapHelper
        );
        MessageController messageController = new MessageControllerImpl(
            graphicsRender
        );
        gameController = new GameController(mapController, messageController);
        ActionAbstractFactory actionAbstractFactory = new ActionAbstractFactory(
            gameController,
            mapController,
            characterAnimationFactory,
            playerMovementFactory,
            actionQueue
        );
        GameStartEvent gameStartEvent = new GameStartEvent();

        gameStartEvent.execute(actionAbstractFactory);
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
        } else {
            gameController.pressKey(getGameAction(keyCode));
        }
    }

    protected void keyReleased(final int keyCode) {
        super.keyReleased(keyCode);

        if (isAllowedKey(keyCode)) {
            gameController.releaseKey(keyCode);
        } else {
            gameController.releaseKey(getGameAction(keyCode));
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

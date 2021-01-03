package org.rpgrunner.j2me;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.GameController;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.j2me.graphics.GraphicsRenderImpl;
import org.rpgrunner.j2me.character.CharacterAnimationFactoryImpl;
import org.rpgrunner.j2me.command.PlayerCommandFactoryImpl;

public class GameRunner extends GameCanvas implements Runnable {
    private static final int FRAMES_PER_SECOND = 100;
    private static final int[] ALLOWED_KEYS = new int[]{
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
    private final PlayerCommandFactoryImpl playerCommandFactory;
    private Thread thread;
    private boolean destroyed;
    private GameController gameController;

    public GameRunner() {
        super(false);

        destroyed = false;
        characterAnimationFactory = new CharacterAnimationFactoryImpl();
        playerCommandFactory = new PlayerCommandFactoryImpl();
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
        GraphicsRenderImpl graphicsRender = new GraphicsRenderImpl(
            graphics,
            camera
        );
        gameController = new GameController(
            graphicsRender,
            camera,
            characterAnimationFactory,
            playerCommandFactory
        );
    }

    private void executeGame() {
        while (isRunning()) {
            gameController.executeCharacterActions();
            gameController.render();

            repaint();
            flushGraphics();
            try {
                Thread.sleep(FRAMES_PER_SECOND);
            } catch (InterruptedException ie) { }
        }
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

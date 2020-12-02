package org.rpgrunner.runner;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.game.GameController;

public class GameRunner extends GameCanvas implements Runnable {
    private static final int FRAMES_PER_SECOND = 100;
    private Thread thread;
    private boolean destroyed;
    private GameController gameController;

    public GameRunner() {
        super(false);

        destroyed = false;
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
        gameController = new GameController(graphics, getWidth(), getHeight());
    }

    private void executeGame() {
        while (isRunning()) {
            gameController.preRender();
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
        int gameAction;
        if (keyCode == GameCanvas.KEY_NUM2) {
            gameAction = GameCanvas.UP;
        } else if (keyCode == GameCanvas.KEY_NUM4) {
            gameAction = GameCanvas.LEFT;
        } else if (keyCode == GameCanvas.KEY_NUM6) {
            gameAction = GameCanvas.RIGHT;
        } else if (keyCode == GameCanvas.KEY_NUM8) {
            gameAction = GameCanvas.DOWN;
        } else if (keyCode == GameCanvas.KEY_NUM5) {
            gameAction = GameCanvas.FIRE;
        } else {
            gameAction = getGameAction(keyCode);
        }
        gameController.setGameAction(gameAction);
    }

    protected void keyReleased(final int keyCode) {
        super.keyReleased(keyCode);
        gameController.setGameAction(-1);
    }
}

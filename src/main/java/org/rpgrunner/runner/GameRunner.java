package org.rpgrunner.runner;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.game.GameController;

public class GameRunner extends GameCanvas implements Runnable {
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

        render();
    }

    private void configure() {
        Graphics graphics = getGraphics();
        graphics.setFont(Font.getDefaultFont());
        gameController = new GameController(graphics, getWidth(), getHeight());
    }

    private void render() {
        while (isRunning()) {
            gameController.render();

            repaint();
            flushGraphics();
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
        gameController.executeGameAction(gameAction);
    }

    protected void keyReleased(final int keyCode) {
        super.keyReleased(keyCode);
        gameController.executeGameAction(-1);
    }
}

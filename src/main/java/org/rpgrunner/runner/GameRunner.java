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
}

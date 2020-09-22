package org.rpgrunner.runner;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Game extends GameCanvas implements Runnable {
    private Thread thread;
    private boolean destroyed;

    public Game() {
        super(false);

        destroyed = false;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        Graphics graphics = getGraphics();
        graphics.setFont(Font.getDefaultFont());

        render();
    }

    public void render() {
        while (isRunning()) {
            repaint();
            flushGraphics();
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isRunning() {
        return !destroyed;
    }
}

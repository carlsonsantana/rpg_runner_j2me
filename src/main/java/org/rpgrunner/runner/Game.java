package org.rpgrunner.runner;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.midlet.MIDlet;

public class Game extends GameCanvas implements Runnable {
    private Thread thread;

    public Game(final MIDlet midlet) {
        super(false);
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
        while (true) {
            repaint();
            flushGraphics();
        }
    }
}

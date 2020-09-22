package org.rpgrunner.runner;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainMIDlet extends MIDlet {
    private final Game game;

    public MainMIDlet() {
        game = new Game(this);
    }

    protected void startApp() {
        Display display = Display.getDisplay(this);
        display.setCurrent(game);
        game.start();
    }

    protected void pauseApp() { }

    protected void destroyApp(
        final boolean unconditional
    ) throws MIDletStateChangeException {
        notifyDestroyed();
    }
}

package org.rpgrunner.runner;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainMIDlet extends MIDlet {
    private final Game game;
    private final MidletCommands midletCommands;

    public MainMIDlet() {
        game = new Game();
        midletCommands = new MidletCommands(this, game);
    }

    protected void startApp() {
        Display display = Display.getDisplay(this);
        display.setCurrent(game);
        game.start();
        midletCommands.setCommands();
    }

    protected void pauseApp() { }

    protected void destroyApp(
        final boolean unconditional
    ) throws MIDletStateChangeException {
        notifyDestroyed();
    }
}

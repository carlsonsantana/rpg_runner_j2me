package org.rpgrunner.j2me;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainMIDlet extends MIDlet {
    private final GameRunner gameRunner;
    private final MidletCommands midletCommands;

    public MainMIDlet() {
        gameRunner = new GameRunner();
        midletCommands = new MidletCommands(this, gameRunner);
    }

    protected void startApp() {
        Display display = Display.getDisplay(this);
        display.setCurrent(gameRunner);
        gameRunner.start();
        midletCommands.setCommands();
    }

    protected void pauseApp() { }

    protected void destroyApp(
        final boolean unconditional
    ) throws MIDletStateChangeException {
        notifyDestroyed();
    }
}

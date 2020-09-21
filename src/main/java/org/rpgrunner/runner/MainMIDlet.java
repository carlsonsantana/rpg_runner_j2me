package org.rpgrunner.runner;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainMIDlet extends MIDlet {
    public MainMIDlet() { }

    protected void startApp() { }

    protected void pauseApp() { }

    protected void destroyApp(
        final boolean unconditional
    ) throws MIDletStateChangeException {
        notifyDestroyed();
    }
}

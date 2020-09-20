package org.groupid.artifactid;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainMIDlet extends MIDlet {
    public MainMIDlet() {
        /*Intialise*/
    }

    /*Enters MIDlet into active state*/
    protected void startApp()  {
        /*Do all your getDisplay() , setCurrent(), and other one time initialization*/
    }

    /*Enters MIDlet into pause state*/
    protected void pauseApp() {}

    /*Enters MIDlet into destroy state*/
    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        notifyDestroyed();
    }
}

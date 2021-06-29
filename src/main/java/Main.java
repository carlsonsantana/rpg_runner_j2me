import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.rpgrunner.j2me.GameRunner;
import org.rpgrunner.j2me.MidletCommands;

public class Main extends MIDlet {
    private final GameRunner gameRunner;
    private final MidletCommands midletCommands;

    public Main() {
        gameRunner = new GameRunner();
        midletCommands = new MidletCommands(this, gameRunner);
    }

    protected void startApp() {
        Display display = Display.getDisplay(this);
        display.setCurrent(gameRunner);
        gameRunner.start();
    }

    protected void pauseApp() { }

    protected void destroyApp(
        final boolean unconditional
    ) throws MIDletStateChangeException {
        gameRunner.destroy();
        notifyDestroyed();
    }
}

package org.rpgrunner.j2me;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class MidletCommands implements CommandListener {
    private final MIDlet midlet;
    private final Command exitCommand;

    public MidletCommands(
        final MIDlet gameMidlet,
        final GameRunner gameRunner
    ) {
        midlet = gameMidlet;
        exitCommand = new Command("Exit", Command.EXIT, 0);
        gameRunner.addCommand(exitCommand);
        gameRunner.setCommandListener(this);
    }

    public void commandAction(
        final Command command,
        final Displayable displayable
    ) {
        if (command == exitCommand) {
            midlet.notifyDestroyed();
        }
    }
}

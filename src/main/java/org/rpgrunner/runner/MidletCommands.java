package org.rpgrunner.runner;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class MidletCommands {
    private final MIDlet midlet;
    private final Game game;

    public MidletCommands(final MIDlet gameMidlet, final Game gameRender) {
        this.midlet = gameMidlet;
        this.game = gameRender;
    }

    public void setCommands() {
        Command exit = new Command("Exit", Command.EXIT, 0);
        game.addCommand(exit);
        game.setCommandListener(new CommandListener() {
            public void commandAction(
                final Command command,
                final Displayable displayable
            ) {
                if (command.getCommandType() == Command.EXIT) {
                    Game gameRender = (Game) displayable;
                    gameRender.destroy();
                    midlet.notifyDestroyed();
                }
            }
        });
    }
}

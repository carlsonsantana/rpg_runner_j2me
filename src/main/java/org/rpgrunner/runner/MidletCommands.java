package org.rpgrunner.runner;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class MidletCommands {
    private final MIDlet midlet;
    private final GameRunner gameRunner;

    public MidletCommands(
        final MIDlet gameMidlet,
        final GameRunner game
    ) {
        this.midlet = gameMidlet;
        this.gameRunner = game;
    }

    public void setCommands() {
        Command exit = new Command("Exit", Command.EXIT, 0);
        gameRunner.addCommand(exit);
        gameRunner.setCommandListener(new CommandListener() {
            public void commandAction(
                final Command command,
                final Displayable displayable
            ) {
                if (command.getCommandType() == Command.EXIT) {
                    GameRunner game = (GameRunner) displayable;
                    game.destroy();
                    midlet.notifyDestroyed();
                }
            }
        });
    }
}

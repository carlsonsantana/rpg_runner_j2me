package org.rpgrunner.j2me.command;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.command.PlayerCommand;
import org.rpgrunner.character.GameCharacter;

public class PlayerCommandImpl implements PlayerCommand {
    private final GameCharacter character;
    private int key;

    public PlayerCommandImpl(final GameCharacter playerCharacter) {
        character = playerCharacter;
        key = -1;
    }

    public void execute() {
        if ((key == GameCanvas.UP) || (key == GameCanvas.KEY_NUM2)) {
            character.moveUp();
        } else if ((key == GameCanvas.RIGHT) || (key == GameCanvas.KEY_NUM6)) {
            character.moveRight();
        } else if ((key == GameCanvas.DOWN) || (key == GameCanvas.KEY_NUM8)) {
            character.moveDown();
        } else if ((key == GameCanvas.LEFT) || (key == GameCanvas.KEY_NUM4)) {
            character.moveLeft();
        }
    }

    public void pressKey(final int keyPressed) {
        key = keyPressed;
    }
}

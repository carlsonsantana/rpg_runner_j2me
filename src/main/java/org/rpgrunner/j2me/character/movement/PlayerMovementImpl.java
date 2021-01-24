package org.rpgrunner.j2me.character.movement;

import javax.microedition.lcdui.game.GameCanvas;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;

public class PlayerMovementImpl implements PlayerMovement {
    private static final int MAX_KEY_SIZE = 10;
    private final GameCharacter character;
    private final int[] keys;
    private int keySize;

    public PlayerMovementImpl(final GameCharacter playerCharacter) {
        character = playerCharacter;
        keys = new int[MAX_KEY_SIZE];
        keySize = 0;
    }

    public void execute() {
        if (keySize == 0) {
            return;
        }

        int key = keys[keySize - 1];

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
        keys[keySize++] = keyPressed;
    }

    public void releaseKey(final int keyReleased) {
        int indexKeyReleased = getKeyIndex(keyReleased);

        if (indexKeyReleased >= 0) {
            for (int i = indexKeyReleased + 1; i < keySize; i++) {
                int key = keys[i];
                keys[i - 1] = key;
            }

            keySize--;
        }
    }

    private int getKeyIndex(final int keySearched) {
        for (int i = 0; i < keySize; i++) {
            int key = keys[i];

            if (key == keySearched) {
                return i;
            }
        }

        return -1;
    }
}

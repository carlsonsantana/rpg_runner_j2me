package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.j2me.Key;

public class PlayerMovementImpl implements PlayerMovement {
    private static final int MAX_KEY_SIZE = 10;
    private final GameCharacter character;
    private final int[] keys;
    private int keySize;
    private boolean actionKeyReleased;

    public PlayerMovementImpl(final GameCharacter playerCharacter) {
        character = playerCharacter;
        keys = new int[MAX_KEY_SIZE];
        keySize = 0;
        actionKeyReleased = false;
    }

    public void execute() {
        if (actionKeyReleased) {
            character.interact();
            actionKeyReleased = false;
        }

        if (keySize == 0) {
            return;
        }

        int key = keys[keySize - 1];

        if (Key.isUp(key)) {
            character.moveUp();
        } else if (Key.isRight(key)) {
            character.moveRight();
        } else if (Key.isDown(key)) {
            character.moveDown();
        } else if (Key.isLeft(key)) {
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

            if (Key.isAction(keyReleased)) {
                actionKeyReleased = true;
            }
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

    public void releaseAllKeys() {
        keySize = 0;
    }
}

package org.rpgrunner.character.movement;

public interface PlayerMovement extends MovementCommand {
    void pressKey(int key);
    void releaseKey(int key);
}

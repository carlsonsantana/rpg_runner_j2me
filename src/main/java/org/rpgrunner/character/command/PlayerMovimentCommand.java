package org.rpgrunner.character.command;

public interface PlayerMovimentCommand extends MovimentCommand {
    void pressKey(int key);
    void releaseKey(int key);
}

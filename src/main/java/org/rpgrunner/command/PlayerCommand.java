package org.rpgrunner.command;

public interface PlayerCommand extends Command {
    void pressKey(int key);
    void releaseKey(int key);
}

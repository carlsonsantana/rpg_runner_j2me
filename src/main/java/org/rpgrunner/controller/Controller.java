package org.rpgrunner.controller;

public interface Controller {
    void pressKey(int key);
    void releaseKey(int key);
    void prepareFrameAnimation();
}

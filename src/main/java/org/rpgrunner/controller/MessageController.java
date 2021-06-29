package org.rpgrunner.controller;

public interface MessageController extends Controller {
    void showMessage(String message);
    boolean isFinished();
}

package org.rpgrunner.controller;

import org.rpgrunner.graphics.GraphicsRender;

public class GameController implements Controller {
    private final GraphicsRender graphicsRender;
    private final MapController mapController;
    private final MessageController messageController;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final MapController gameMapController,
        final MessageController gameMessageController
    ) {
        graphicsRender = gameGraphicsRender;
        mapController = gameMapController;
        messageController = gameMessageController;
    }

    public void pressKey(final int key) {
        mapController.pressKey(key);
    }

    public void releaseKey(final int key) {
        mapController.releaseKey(key);
    }

    public void prepareFrameAnimation() {
        mapController.prepareFrameAnimation();
    }

    public void render() {
        graphicsRender.render();
    }

    public void showMessage(final String message) {
        messageController.showMessage(message);
    }
}

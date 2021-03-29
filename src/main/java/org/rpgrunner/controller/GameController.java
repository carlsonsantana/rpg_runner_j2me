package org.rpgrunner.controller;

import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;

public class GameController implements Controller {
    private final GraphicsRender graphicsRender;
    private final MapController mapController;

    public GameController(
        final ActionQueue actionQueue,
        final GraphicsRender gameGraphicsRender,
        final Camera camera
    ) {
        graphicsRender = gameGraphicsRender;
        mapController = new MapController(actionQueue, graphicsRender, camera);
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
        graphicsRender.showMessage(message);
    }

    public MapController getMapController() {
        return mapController;
    }
}

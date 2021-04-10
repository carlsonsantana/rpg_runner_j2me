package org.rpgrunner.controller;

import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.MapHelper;

public class GameController implements Controller {
    private final GraphicsRender graphicsRender;
    private final MapController mapController;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera camera,
        final MapHelper mapHelper
    ) {
        graphicsRender = gameGraphicsRender;
        mapController = new MapController(graphicsRender, camera, mapHelper);
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

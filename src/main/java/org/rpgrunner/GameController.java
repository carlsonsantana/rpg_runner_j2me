package org.rpgrunner;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.MapHelper;

public class GameController {
    private final GraphicsRender graphicsRender;
    private final MapController mapController;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera camera
    ) {
        graphicsRender = gameGraphicsRender;
        mapController = new MapController(this, graphicsRender, camera);
    }

    public void pressKey(final int key) {
        mapController.pressKey(key);
    }

    public void releaseKey(final int key) {
        mapController.releaseKey(key);
    }

    public void executeCharacterActions() {
        mapController.executeCharacterActions();
    }

    public void render() {
        graphicsRender.render();
    }

    public MapHelper getMapHelper() {
        return mapController.getMapHelper();
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        mapController.addCharacterElement(characterElement);
    }

    public void executeAction(final Action action) {
        action.execute();
    }

    public void showMessage(final String message) {
        graphicsRender.showMessage(message);
    }

    public MapController getMapController() {
        return mapController;
    }
}

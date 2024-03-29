package org.rpgrunner.controller;

public class GameController implements Controller {
    private final MapController mapController;
    private final MessageController messageController;
    private Controller currentController;

    public GameController(
        final MapController gameMapController,
        final MessageController gameMessageController
    ) {
        mapController = gameMapController;
        messageController = gameMessageController;
        currentController = mapController;
    }

    public void prepareFrameAnimation() {
        removeFinishedControllers();

        currentController.prepareFrameAnimation();
    }

    private void removeFinishedControllers() {
        if (
            (currentController == messageController)
            && (messageController.isFinished())
        ) {
            currentController = mapController;
        }
    }

    public void render() {
        currentController.render();
    }

    public void showMessage(final String message) {
        messageController.showMessage(message);
        currentController = messageController;
    }
}

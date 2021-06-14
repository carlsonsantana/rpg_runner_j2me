package org.rpgrunner.j2me.graphics;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.graphics.MessageGraphicsRender;
import org.rpgrunner.helper.Camera;

public class MessageGraphicsRenderImpl implements MessageGraphicsRender {
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final Camera camera;
    private MessageDialog currentMessageDialog;

    public MessageGraphicsRenderImpl(
        final Graphics midletGraphics,
        final Camera gameCamera
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();
        camera = gameCamera;
    }

    public void showMessage(final String message) {
        currentMessageDialog = new MessageDialog(message, graphics, camera);
    }

    public void hideMessage() {
        currentMessageDialog = null;
    }

    public void render() {
        showCurrentMessage();
    }

    private void showCurrentMessage() {
        if (currentMessageDialog == null) {
            return;
        }

        currentMessageDialog.display();
    }
}

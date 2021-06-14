package org.rpgrunner.j2me.graphics;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.map.Map;

public class MessageGraphicsRender implements GraphicsRender {
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final Camera camera;
    private MessageDialog currentMessageDialog;

    public MessageGraphicsRender(
        final Graphics midletGraphics,
        final Camera gameCamera
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();
        camera = gameCamera;
    }

    public void setMap(final Map map) { }

    private void clearMapLayerManager() { }

    public void setCharacterElements(final Vector characterElements) { }

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

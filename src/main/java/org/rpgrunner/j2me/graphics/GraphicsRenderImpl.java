package org.rpgrunner.j2me.graphics;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Layer;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.map.Map;
import org.rpgrunner.j2me.MapRender;

public class GraphicsRenderImpl implements GraphicsRender {
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final Camera camera;

    public GraphicsRenderImpl(
        final Graphics midletGraphics,
        final Camera gameCamera
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();
        camera = gameCamera;
    }

    public void setMap(final Map map) {
        clearMapLayerManager();
        MapRender mapRender = new MapRender(map);
        TiledLayer[] tiledLayers = mapRender.getTiledLayers();
        for (int i = tiledLayers.length - 1; i >= 0; i--) {
            layerManager.append(tiledLayers[i]);
        }
    }

    private void clearMapLayerManager() {
        int size = layerManager.getSize();
        for (int i = 1; i < size; i++) {
            Layer layer = layerManager.getLayerAt(size - i);
            if (layer instanceof TiledLayer) {
                layerManager.remove(layer);
            }
        }
    }

    public void setCharacterElements(
        final CharacterElement[] characterElements
    ) {
        for (int i = 0; i < characterElements.length; i++) {
            CharacterAnimation characterAnimation = (
                characterElements[i].getCharacterAnimation()
            );
            layerManager.insert((Sprite) characterAnimation.getSprite(), 0);
        }
    }

    private void clearCharacterLayerManager() {
        int size = layerManager.getSize();
        for (int i = 1; i < size; i++) {
            Layer layer = layerManager.getLayerAt(size - i);
            if (layer instanceof Sprite) {
                layerManager.remove(layer);
            }
        }
    }

    public void render() {
        centerCamera();
        layerManager.paint(graphics, 0, 0);
    }

    private void centerCamera() {
        camera.centerCamera();
        layerManager.setViewWindow(
            camera.getX(),
            camera.getY(),
            camera.getScreenWidth(),
            camera.getScreenHeight()
        );
    }
}

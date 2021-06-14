package org.rpgrunner.j2me.graphics;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Layer;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.j2me.map.MapRender;
import org.rpgrunner.map.Map;

public class MapGraphicsRenderImpl implements MapGraphicsRender {
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final Camera camera;

    public MapGraphicsRenderImpl(
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

    public void setCharacterElements(final Vector characterElements) {
        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement characterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            CharacterAnimation characterAnimation = (
                characterElement.getCharacterAnimation()
            );
            layerManager.insert((Sprite) characterAnimation.getSprite(), 0);
        }
    }

    public void showMessage(final String message) { }

    public void hideMessage() { }

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

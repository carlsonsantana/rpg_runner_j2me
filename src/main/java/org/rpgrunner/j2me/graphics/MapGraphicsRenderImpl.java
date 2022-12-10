package org.rpgrunner.j2me.graphics;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Layer;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.j2me.map.MapRender;
import org.rpgrunner.map.Map;

public class MapGraphicsRenderImpl implements MapGraphicsRender {
    private static final int TILE_WIDTH = 16;
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final int screenWidth;
    private final int screenHeight;
    private final GameCharacter[] characters;
    private Map map;
    private GameCharacter characterAnimationFollowed;

    public MapGraphicsRenderImpl(
        final Graphics midletGraphics,
        final int currentScreenWidth,
        final int currentScreenHeight,
        final GameCharacter[] gameCharacters
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();
        screenWidth = currentScreenWidth;
        screenHeight = currentScreenHeight;
        characters = gameCharacters;
    }

    public void setMap(final Map newMap) {
        clearMapLayerManager();
        map = newMap;
        MapRender mapRender = new MapRender(newMap);
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

    public void notifyChangesCharacterElements() {
        for (
            int i = 0, length = characters.length;
            (i < length) && (characters[i] != null);
            i++
        ) {
            GameCharacter character = characters[i];
            layerManager.insert((Sprite) character.getSprite(), 0);
        }
    }

    public void setCharacterAnimation(
        final GameCharacter newCharacterAnimation
    ) {
        characterAnimationFollowed = newCharacterAnimation;
    }

    public void render() {
        int xViewWindow = getXViewWindow();
        int yViewWindow = getYViewWindow();

        layerManager.setViewWindow(
            xViewWindow,
            yViewWindow,
            screenWidth,
            screenHeight
        );
        layerManager.paint(graphics, 0, 0);
    }

    private int getXViewWindow() {
        int characterScreenPosition = (
            characterAnimationFollowed.getScreenX() + (TILE_WIDTH / 2)
        );

        return getCenter(characterScreenPosition, screenWidth, map.getWidth());
    }

    private int getYViewWindow() {
        return getCenter(
            characterAnimationFollowed.getScreenY(),
            screenHeight,
            map.getHeight()
        );
    }

    private int getCenter(
        final int characterScreenPosition,
        final int screenSize,
        final int mapSize
    ) {
        int screenMiddle = screenSize / 2;
        int characterPosition = characterScreenPosition;
        int max = (mapSize * TILE_WIDTH) - screenSize;
        int cameraPositionWithoutCorrection = (
            characterPosition - screenMiddle
        );

        return Math.min(Math.max(cameraPositionWithoutCorrection, 0), max);
    }
}

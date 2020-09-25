package org.rpgrunner.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.game.map.Map;

public class GameController {
    private final Graphics graphics;
    private final int screenWidth;
    private final int screenHeight;
    private final LayerManager layerManager;
    private Map map;

    public GameController(
        final Graphics midletGraphics,
        final int deviceScreenWidth,
        final int deviceScreenHeight
    ) {
        graphics = midletGraphics;
        screenWidth = deviceScreenWidth;
        screenHeight = deviceScreenHeight;

        layerManager = new LayerManager();
    }

    public void render() {
        layerManager.paint(graphics, 0, 0);
    }

    public void setMap(final Map newMap) {
        map = newMap;
    }
}

package org.rpgrunner.game;

import javax.microedition.lcdui.Graphics;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapLoader;
import org.rpgrunner.game.map.MapRender;
import org.rpgrunner.game.j2me.MapRenderImpl;

import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterRender;
import org.rpgrunner.game.j2me.CharacterRenderImpl;

public class GameController {
    private final Graphics graphics;
    private final int screenWidth;
    private final int screenHeight;
    private Map map;
    private MapRender mapRender;
    private GameCharacter playerCharacter;
    private CharacterRender characterRender;

    public GameController(
        final Graphics midletGraphics,
        final int deviceScreenWidth,
        final int deviceScreenHeight
    ) {
        graphics = midletGraphics;
        screenWidth = deviceScreenWidth;
        screenHeight = deviceScreenHeight;

        setMap(MapLoader.loadMap("map"));
        setPlayerCharacter(new GameCharacter("character"));
    }

    public void setMap(final Map newMap) {
        map = newMap;
        mapRender = new MapRenderImpl(graphics, map);
    }

    public void setPlayerCharacter(final GameCharacter newPlayerCharacter) {
        playerCharacter = newPlayerCharacter;
        characterRender = new CharacterRenderImpl(graphics, playerCharacter);
    }

    public void render() {
        mapRender.render();
        characterRender.render();
    }
}

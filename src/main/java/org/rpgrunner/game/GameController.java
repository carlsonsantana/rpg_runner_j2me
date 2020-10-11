package org.rpgrunner.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.TiledLayer;
import javax.microedition.lcdui.game.Layer;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapLoader;
import org.rpgrunner.game.j2me.MapRender;

import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.j2me.CharacterRender;

public class GameController {
    private static final int TILE_WIDTH = 16;
    private static final int SPRITE_SPEED = 4;
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final int screenWidth;
    private final int screenHeight;
    private final int screenMiddleWidth;
    private final int screenMiddleHeight;
    private int cameraPositionX;
    private int cameraPositionY;
    private Map map;
    private GameCharacter playerCharacter;
    private CharacterRender characterRender;
    private int gameAction;

    public GameController(
        final Graphics midletGraphics,
        final int deviceScreenWidth,
        final int deviceScreenHeight
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();
        screenWidth = deviceScreenWidth;
        screenHeight = deviceScreenHeight;
        screenMiddleWidth = screenWidth / 2;
        screenMiddleHeight = screenHeight / 2;
        cameraPositionX = 0;
        cameraPositionY = 0;

        setMap(MapLoader.loadMap("map"));
        setPlayerCharacter(new GameCharacter("character"));
    }

    public void setMap(final Map newMap) {
        map = newMap;
        MapRender mapRender = new MapRender(map);
        clearMapLayerManager();
        TiledLayer[] tiledLayers = mapRender.getTiledLayers();
        for (int i = tiledLayers.length - 1; i >= 0; i--) {
            layerManager.append(tiledLayers[i]);
        }
    }

    private void clearMapLayerManager() {
        int size = layerManager.getSize();
        for (int i = 1; (i < size) && (i <= 2); i++) {
            Layer layer = layerManager.getLayerAt(size - i);
            if (layer instanceof TiledLayer) {
                layerManager.remove(layer);
            }
        }
    }

    public void setPlayerCharacter(final GameCharacter newPlayerCharacter) {
        playerCharacter = newPlayerCharacter;
        characterRender = new CharacterRender(playerCharacter);
        layerManager.insert(characterRender.render(), 0);
    }

    public void setGameAction(final int newGameAction) {
        gameAction = newGameAction;
    }

    public void preRender() {
        if (gameAction == GameCanvas.UP) {
            playerCharacter.moveUp();
        } else if (gameAction == GameCanvas.RIGHT) {
            playerCharacter.moveRight();
        } else if (gameAction == GameCanvas.DOWN) {
            playerCharacter.moveDown();
        } else if (gameAction == GameCanvas.LEFT) {
            playerCharacter.moveLeft();
        }
        if (
            !map.canMoveTo(
                playerCharacter.getMapPositionX(),
                playerCharacter.getMapPositionY(),
                playerCharacter.getMapNextPositionX(),
                playerCharacter.getMapNextPositionY(),
                playerCharacter.getDirection()
            )
        ) {
            playerCharacter.cancelMove();
        }
    }

    public void render() {
        characterRender.preRender();
        centerCamera();
        layerManager.setViewWindow(
            cameraPositionX,
            cameraPositionY,
            screenWidth,
            screenHeight
        );
        layerManager.paint(graphics, 0, 0);
    }

    private void centerCamera() {
        int playerAbsolutePositionX = characterRender.getX();
        int playerAbsolutePositionY = characterRender.getY();
        int screenCharacterPositionX = (
            playerAbsolutePositionX - cameraPositionX
        );
        int screenCharacterPositionY = (
            playerAbsolutePositionY - cameraPositionY
        );
        int middleCharacterWidth = characterRender.getWidth() / 2;
        int middleCharacterHeight = characterRender.getHeight() / 2;
        int middleCharacterPositionScreenX = (
            screenCharacterPositionX + middleCharacterWidth
        );
        int middleCharacterPositionScreenY = (
            screenCharacterPositionY + middleCharacterHeight
        );
        int mapWidth = map.getWidth() * TILE_WIDTH;
        int mapHeight = map.getHeight() * TILE_WIDTH;
        byte direction = playerCharacter.getDirection();

        if (
            (Direction.isRight(direction))
            && (screenMiddleWidth < middleCharacterPositionScreenX)
            && ((playerAbsolutePositionX + screenMiddleWidth) <= mapWidth)
        ) {
            cameraPositionX += SPRITE_SPEED;
        } else if (
            (Direction.isLeft(direction))
            && (screenMiddleWidth > middleCharacterPositionScreenX)
            && (cameraPositionX > 0)
        ) {
            cameraPositionX -= SPRITE_SPEED;
        } else if (
            (Direction.isDown(direction))
            && (screenMiddleHeight < middleCharacterPositionScreenY)
            && ((playerAbsolutePositionY + screenMiddleHeight) <= mapHeight)
        ) {
            cameraPositionY += SPRITE_SPEED;
        } else if (
            (Direction.isUp(direction))
            && (screenMiddleHeight > middleCharacterPositionScreenY)
            && (cameraPositionY > 0)
        ) {
            cameraPositionY -= SPRITE_SPEED;
        }
    }

    public void posRender() {
        if (characterRender.isAnimationComplete()) {
            playerCharacter.finishMove();
        }
    }
}

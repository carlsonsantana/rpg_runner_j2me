package org.rpgrunner.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapLoader;
import org.rpgrunner.game.j2me.MapRender;

import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterRender;
import org.rpgrunner.game.j2me.CharacterRenderImpl;

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
        for (int i = 0; (i < size) && (i < 2); i++) {
            layerManager.remove(layerManager.getLayerAt(--size));
            layerManager.remove(layerManager.getLayerAt(--size));
        }
    }

    public void setPlayerCharacter(final GameCharacter newPlayerCharacter) {
        playerCharacter = newPlayerCharacter;
        characterRender = new CharacterRenderImpl(graphics, playerCharacter);
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
        characterRender.render();
    }

    private void centerCamera() {
        int screenCharacterPositionX = characterRender.getX();
        int screenCharacterPositionY = characterRender.getY();
        int middleCharacterWidth = characterRender.getWidth() / 2;
        int middleCharacterHeight = characterRender.getHeight() / 2;
        int middleCharacterPositionScreenX = (
            screenCharacterPositionX + middleCharacterWidth
        );
        int middleCharacterPositionScreenY = (
            screenCharacterPositionY + middleCharacterHeight
        );
        int playerAbsolutePositionX = (
            cameraPositionX
            + screenCharacterPositionX
            + middleCharacterWidth
        );
        int playerAbsolutePositionY = (
            cameraPositionY
            + screenCharacterPositionY
            + middleCharacterHeight
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
            centerPlayerScreenX();
        } else if (
            (Direction.isLeft(direction))
            && (screenMiddleWidth > middleCharacterPositionScreenX)
            && (cameraPositionX > 0)
        ) {
            cameraPositionX -= SPRITE_SPEED;
            centerPlayerScreenX();
        } else if (
            (Direction.isDown(direction))
            && (screenMiddleHeight < middleCharacterPositionScreenY)
            && ((playerAbsolutePositionY + screenMiddleHeight) <= mapHeight)
        ) {
            cameraPositionY += SPRITE_SPEED;
            centerPlayerScreenY();
        } else if (
            (Direction.isUp(direction))
            && (screenMiddleHeight > middleCharacterPositionScreenY)
            && (cameraPositionY > 0)
        ) {
            cameraPositionY -= SPRITE_SPEED;
            centerPlayerScreenY();
        }
    }

    private void centerPlayerScreenX() {
        int middleCharacterWidth = characterRender.getWidth() / 2;
        int screenCharacterPositionX = screenMiddleWidth - middleCharacterWidth;
        int screenCharacterPositionY = characterRender.getY();
        characterRender.setPosition(
            screenCharacterPositionX,
            screenCharacterPositionY
        );
    }

    private void centerPlayerScreenY() {
        int middleCharacterHeight = characterRender.getHeight() / 2;
        int screenCharacterPositionX = characterRender.getX();
        int screenCharacterPositionY = (
            screenMiddleHeight - middleCharacterHeight
        );
        characterRender.setPosition(
            screenCharacterPositionX,
            screenCharacterPositionY
        );
    }

    public void posRender() {
        if (characterRender.isAnimationComplete()) {
            playerCharacter.finishMove();
        }
    }
}

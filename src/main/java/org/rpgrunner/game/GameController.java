package org.rpgrunner.game;

import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Layer;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

import org.rpgrunner.game.character.CharacterAnimation;
import org.rpgrunner.game.character.CharacterElement;
import org.rpgrunner.game.character.CharacterMovimentEvent;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.helper.CollisionDetector;
import org.rpgrunner.game.j2me.CharacterAnimationImpl;
import org.rpgrunner.game.j2me.MapRender;
import org.rpgrunner.game.map.Map;
import org.rpgrunner.game.map.MapLoader;

public class GameController {
    private static final int TILE_WIDTH = 16;
    private static final int SPRITE_SPEED = 4;
    private final Graphics graphics;
    private final LayerManager layerManager;
    private final int screenWidth;
    private final int screenHeight;
    private final int screenMiddleWidth;
    private final int screenMiddleHeight;
    private final CollisionDetector collisionDetector;
    private final CharacterMovimentEvent characterMovimentEvent;
    private int cameraPositionX;
    private int cameraPositionY;
    private Map map;
    private GameCharacter playerCharacter;
    private CharacterAnimation playerCharacterAnimation;
    private CharacterAnimation[] characters;
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

        collisionDetector = new CollisionDetector();
        characterMovimentEvent = new CharacterMovimentEvent(collisionDetector);

        setMap(MapLoader.loadMap("map"));
        setPlayerCharacter(new GameCharacter("character"));
        setCharacters();
    }

    public void setMap(final Map newMap) {
        map = newMap;
        MapRender mapRender = new MapRender(map);
        clearMapLayerManager();
        TiledLayer[] tiledLayers = mapRender.getTiledLayers();
        for (int i = tiledLayers.length - 1; i >= 0; i--) {
            layerManager.append(tiledLayers[i]);
        }

        collisionDetector.setMap(map);
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
        playerCharacterAnimation = new CharacterAnimationImpl(playerCharacter);
        CharacterElement characterElement = new CharacterElement(
            characterMovimentEvent,
            playerCharacter,
            playerCharacterAnimation
        );
        playerCharacter.setCharacterElement(characterElement);
    }

    private void setCharacters() {
        GameCharacter character = new GameCharacter("character");
        CharacterAnimation characterAnimation = new CharacterAnimationImpl(
            character
        );
        CharacterElement characterElement = new CharacterElement(
            characterMovimentEvent,
            character,
            characterAnimation
        );
        character.setCharacterElement(characterElement);

        characters = new CharacterAnimation[2];
        characters[0] = playerCharacterAnimation;
        characters[1] = characterAnimation;
        for (int i = 0; i < characters.length; i++) {
            layerManager.insert((Sprite) characters[i].getSprite(), 0);
        }

        collisionDetector.setCharacters(
            new GameCharacter[] {playerCharacter, character}
        );
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

        moveNPCs();
        avoidCollisions();
    }

    private void moveNPCs() {
        for (int i = 0; i < characters.length; i++) {
            CharacterAnimation characterAnimation = characters[i];

            if (characterAnimation != playerCharacterAnimation) {
                GameCharacter character = characterAnimation.getCharacter();
                Random random = new Random();
                int direction = random.nextInt(Direction.NUMBER_DIRECTIONS);
                if (direction == 0) {
                    character.moveUp();
                } else if (direction == 1) {
                    character.moveRight();
                } else if (direction == 2) {
                    character.moveDown();
                } else {
                    character.moveLeft();
                }
            }
        }
    }

    private void avoidCollisions() {
        for (int i = 0; i < characters.length; i++) {
            CharacterAnimation characterAnimation = characters[i];
            GameCharacter character = characterAnimation.getCharacter();

            if (
                !map.canMoveTo(
                    character.getMapPositionX(),
                    character.getMapPositionY(),
                    character.getMapNextPositionX(),
                    character.getMapNextPositionY(),
                    character.getDirection()
                )
            ) {
                character.cancelMove();
            }
        }
    }

    public void render() {
        preRenderCharacters();
        centerCamera();
        layerManager.setViewWindow(
            cameraPositionX,
            cameraPositionY,
            screenWidth,
            screenHeight
        );
        layerManager.paint(graphics, 0, 0);
    }

    private void preRenderCharacters() {
        for (int i = 0; i < characters.length; i++) {
            CharacterAnimation characterAnimation = characters[i];

            characterAnimation.startAnimation();
        }
    }

    private void centerCamera() {
        int playerAbsolutePositionX = playerCharacterAnimation.getScreenX();
        int playerAbsolutePositionY = playerCharacterAnimation.getScreenY();
        int screenCharacterPositionX = (
            playerAbsolutePositionX - cameraPositionX
        );
        int screenCharacterPositionY = (
            playerAbsolutePositionY - cameraPositionY
        );
        int middleCharacterWidth = playerCharacterAnimation.getWidth() / 2;
        int middleCharacterHeight = playerCharacterAnimation.getHeight() / 2;
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
        for (int i = 0; i < characters.length; i++) {
            CharacterAnimation characterAnimation = characters[i];
            GameCharacter character = characterAnimation.getCharacter();

            if (characterAnimation.isAnimationComplete()) {
                character.finishMove();
            }
        }
    }
}

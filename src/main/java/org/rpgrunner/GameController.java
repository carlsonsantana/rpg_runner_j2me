package org.rpgrunner;

import javax.microedition.lcdui.Graphics;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.command.Command;
import org.rpgrunner.command.PlayerCommand;
import org.rpgrunner.command.RandomCommand;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.j2me.CharacterAnimationImpl;
import org.rpgrunner.j2me.command.PlayerCommandImpl;
import org.rpgrunner.j2me.graphics.GraphicsRenderImpl;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class GameController {
    private final Camera camera;
    private final CollisionDetector collisionDetector;
    private final GraphicsRender graphicsRender;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerCommand playerCommand;
    private CharacterElement[] characterElements;
    private int gameAction;

    public GameController(
        final Graphics graphics,
        final int screenWidth,
        final int screenHeight
    ) {
        camera = new Camera(screenWidth, screenHeight);
        graphicsRender = new GraphicsRenderImpl(graphics, camera);

        collisionDetector = new CollisionDetector();

        setMap(MapLoader.loadMap("map"));
        playerCharacterElement = generatePlayerCharacterElement("character");
        setCharacters();
    }

    public void setMap(final Map newMap) {
        map = newMap;

        collisionDetector.setMap(map);
        camera.setMap(map);
        graphicsRender.setMap(map);
    }

    private void setCharacters() {
        CharacterElement characterElement = generateNPCCharacterElement(
            "character"
        );

        characterElements = new CharacterElement[2];
        characterElements[0] = playerCharacterElement;
        characterElements[1] = characterElement;

        collisionDetector.setCharacters(new GameCharacter[] {
            playerCharacterElement.getCharacter(),
            characterElement.getCharacter()
        });
        graphicsRender.setCharacterElements(characterElements);
    }

    private CharacterElement generateNPCCharacterElement(
        final String baseName
    ) {
        GameCharacter character = new GameCharacter(baseName);
        RandomCommand command = new RandomCommand(character);

        return generateCharacterElement(character, command);
    }

    private CharacterElement generatePlayerCharacterElement(
        final String baseName
    ) {
        GameCharacter character = new GameCharacter(baseName);
        playerCommand = new PlayerCommandImpl(character);
        CharacterElement characterElement = generateCharacterElement(
            character,
            playerCommand
        );
        camera.setCharacterAnimation(characterElement.getCharacterAnimation());

        return characterElement;
    }

    private CharacterElement generateCharacterElement(
        final GameCharacter character,
        final Command command
    ) {
        CharacterAnimation characterAnimation = new CharacterAnimationImpl(
            character
        );
        CharacterElement characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation,
            command
        );
        character.setCharacterElement(characterElement);
        characterAnimation.setCharacterElement(characterElement);

        return characterElement;
    }

    public void pressKey(final int key) {
        playerCommand.pressKey(key);
    }

    public void releaseKey(final int key) {
        playerCommand.releaseKey(key);
    }

    public void preRender() {
        for (int i = 0; i < characterElements.length; i++) {
            CharacterElement characterElement = characterElements[i];
            Command command = characterElement.getCommand();

            command.execute();
        }
    }

    public void render() {
        preRenderCharacters();
        graphicsRender.render();
    }

    private void preRenderCharacters() {
        for (int i = 0; i < characterElements.length; i++) {
            CharacterAnimation characterAnimation = (
                characterElements[i].getCharacterAnimation()
            );

            characterAnimation.doAnimation();
        }
    }
}

package org.rpgrunner;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.command.Command;
import org.rpgrunner.command.PlayerCommand;
import org.rpgrunner.command.PlayerCommandFactory;
import org.rpgrunner.command.RandomCommand;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class GameController {
    private final Camera camera;
    private final CollisionDetector collisionDetector;
    private final GraphicsRender graphicsRender;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final PlayerCommandFactory playerCommandFactory;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerCommand playerCommand;
    private CharacterElement[] characterElements;
    private int gameAction;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera gameCamera,
        final CharacterAnimationFactory gameCharacterAnimationFactory,
        final PlayerCommandFactory gamePlayerCommandFactory
    ) {
        camera = gameCamera;
        graphicsRender = gameGraphicsRender;
        collisionDetector = new CollisionDetector();
        characterAnimationFactory = gameCharacterAnimationFactory;
        playerCommandFactory = gamePlayerCommandFactory;
    }

    public void init() {
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

    public Map getMap() {
        return map;
    }

    private void setCharacters() {
        CharacterElement characterElement = generateNPCCharacterElement(
            "character"
        );

        characterElements = new CharacterElement[2];
        characterElements[0] = playerCharacterElement;
        characterElements[1] = characterElement;

        GameCharacter[] characters = new GameCharacter[] {
            playerCharacterElement.getCharacter(),
            characterElement.getCharacter()
        };

        collisionDetector.setCharacters(characters);
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
        playerCommand = playerCommandFactory.createPlayerCommand(character);
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
        CharacterAnimation characterAnimation = (
            characterAnimationFactory.createCharacterAnimation(character)
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

    public void executeCharacterActions() {
        for (int i = 0; i < characterElements.length; i++) {
            CharacterElement characterElement = characterElements[i];
            executeCommand(characterElement);
            executeAnimation(characterElement);
        }
    }

    private void executeCommand(final CharacterElement characterElement) {
        Command command = characterElement.getCommand();

        command.execute();
    }

    private void executeAnimation(final CharacterElement characterElement) {
        CharacterAnimation characterAnimation = (
            characterElement.getCharacterAnimation()
        );

        characterAnimation.doAnimation();
    }

    public void render() {
        graphicsRender.render();
    }
}

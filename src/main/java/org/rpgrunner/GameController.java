package org.rpgrunner;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.command.MovimentCommand;
import org.rpgrunner.character.command.PlayerMovimentCommand;
import org.rpgrunner.character.command.PlayerMovimentCommandFactory;
import org.rpgrunner.character.command.RandomMovimentCommand;
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
    private final PlayerMovimentCommandFactory playerMovimentCommandFactory;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerMovimentCommand playerMovimentCommand;
    private CharacterElement[] characterElements;
    private int gameAction;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera gameCamera,
        final CharacterAnimationFactory gameCharacterAnimationFactory,
        final PlayerMovimentCommandFactory gamePlayerMovimentCommandFactory
    ) {
        camera = gameCamera;
        graphicsRender = gameGraphicsRender;
        collisionDetector = new CollisionDetector();
        characterAnimationFactory = gameCharacterAnimationFactory;
        playerMovimentCommandFactory = gamePlayerMovimentCommandFactory;
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
        RandomMovimentCommand command = new RandomMovimentCommand(character);

        return generateCharacterElement(character, command);
    }

    private CharacterElement generatePlayerCharacterElement(
        final String baseName
    ) {
        GameCharacter character = new GameCharacter(baseName);
        playerMovimentCommand = (
            playerMovimentCommandFactory.createPlayerMovimentCommand(character)
        );
        CharacterElement characterElement = generateCharacterElement(
            character,
            playerMovimentCommand
        );
        camera.setCharacterAnimation(characterElement.getCharacterAnimation());

        return characterElement;
    }

    private CharacterElement generateCharacterElement(
        final GameCharacter character,
        final MovimentCommand movimentCommand
    ) {
        CharacterAnimation characterAnimation = (
            characterAnimationFactory.createCharacterAnimation(character)
        );
        CharacterElement characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation,
            movimentCommand
        );
        character.setCharacterElement(characterElement);
        characterAnimation.setCharacterElement(characterElement);

        return characterElement;
    }

    public void pressKey(final int key) {
        playerMovimentCommand.pressKey(key);
    }

    public void releaseKey(final int key) {
        playerMovimentCommand.releaseKey(key);
    }

    public void executeCharacterActions() {
        for (int i = 0; i < characterElements.length; i++) {
            CharacterElement characterElement = characterElements[i];
            executeCommand(characterElement);
            executeAnimation(characterElement);
        }
    }

    private void executeCommand(final CharacterElement characterElement) {
        MovimentCommand command = characterElement.getMovimentCommand();

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

    public CharacterElement getPlayerCharacterElement() {
        return playerCharacterElement;
    }
}

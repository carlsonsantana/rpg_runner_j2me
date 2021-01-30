package org.rpgrunner;

import java.util.Enumeration;
import java.util.Vector;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.character.movement.RandomMovement;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.event.action.Teleport;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.map.Map;

public class GameController {
    private final Camera camera;
    private final CollisionDetector collisionDetector;
    private final GraphicsRender graphicsRender;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final PlayerMovementFactory playerMovementFactory;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerMovement playerMovement;
    private Vector characterElements;
    private int gameAction;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera gameCamera,
        final CharacterAnimationFactory gameCharacterAnimationFactory,
        final PlayerMovementFactory gamePlayerMovementFactory
    ) {
        camera = gameCamera;
        graphicsRender = gameGraphicsRender;
        collisionDetector = new CollisionDetector();
        characterAnimationFactory = gameCharacterAnimationFactory;
        playerMovementFactory = gamePlayerMovementFactory;
        characterElements = new Vector(1);
    }

    public void init() {
        generatePlayerCharacterElement("character");
        Teleport teleport = new Teleport(this, "map", 1, 1);
        teleport.execute();
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

        characterElements.addElement(playerCharacterElement);
        characterElements.addElement(characterElement);

        collisionDetector.setCharacterElements(characterElements);
        graphicsRender.setCharacterElements(characterElements);
    }

    private CharacterElement generateNPCCharacterElement(
        final String baseName
    ) {
        GameCharacter character = new GameCharacter(baseName);
        RandomMovement movementCommand = new RandomMovement(character);

        return generateCharacterElement(character, movementCommand);
    }

    private void generatePlayerCharacterElement(final String baseName) {
        PlayerCharacterCreator playerCharacterCreator = (
            new PlayerCharacterCreator(this, baseName, 0, 0)
        );
        playerCharacterCreator.execute();
    }

    private CharacterElement generateCharacterElement(
        final GameCharacter character,
        final MovementCommand movementCommand
    ) {
        CharacterAnimation characterAnimation = (
            characterAnimationFactory.createCharacterAnimation(character)
        );
        CharacterElement characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation,
            movementCommand
        );
        character.setCharacterElement(characterElement);
        characterAnimation.setCharacterElement(characterElement);

        return characterElement;
    }

    public void pressKey(final int key) {
        playerMovement.pressKey(key);
    }

    public void releaseKey(final int key) {
        playerMovement.releaseKey(key);
    }

    public void executeCharacterActions() {
        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement characterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            executeMovementCommand(characterElement);
            executeAnimation(characterElement);
        }
    }

    private void executeMovementCommand(
        final CharacterElement characterElement
    ) {
        MovementCommand movementCommand = characterElement.getMovementCommand();

        movementCommand.execute();
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

    public PlayerMovementFactory getPlayerMovementFactory() {
        return playerMovementFactory;
    }

    public CharacterAnimationFactory getCharacterAnimationFactory() {
        return characterAnimationFactory;
    }

    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public void setPlayerCharacterElement(
        final CharacterElement newPlayerCharacterElement
    ) {
        playerCharacterElement = newPlayerCharacterElement;
        playerMovement = (
            (PlayerMovement) playerCharacterElement.getMovementCommand()
        );
        camera.setCharacterAnimation(
            playerCharacterElement.getCharacterAnimation()
        );
    }
}

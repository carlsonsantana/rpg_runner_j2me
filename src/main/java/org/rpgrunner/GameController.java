package org.rpgrunner;

import java.util.Enumeration;
import java.util.Vector;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.event.GameStartEvent;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.map.Map;

public class GameController {
    private final GraphicsRender graphicsRender;
    private final Camera camera;
    private final Vector characterElements;
    private final CollisionDetector collisionDetector;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerMovement playerMovement;
    private int gameAction;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera gameCamera
    ) {
        graphicsRender = gameGraphicsRender;
        camera = gameCamera;
        characterElements = new Vector(1);
        collisionDetector = new CollisionDetector();
        collisionDetector.setCharacterElements(characterElements);
    }

    public void executeStartActions(
        final ActionAbstractFactory actionAbstractFactory,
        final GameStartEvent gameStartEvent
    ) {
        gameStartEvent.execute(actionAbstractFactory);
        graphicsRender.setCharacterElements(characterElements);
    }

    public void setMap(final Map newMap) {
        map = newMap;

        collisionDetector.setMap(map);
        camera.setMap(map);
        graphicsRender.setMap(map);

        map.executeStartActions();
    }

    public Map getMap() {
        return map;
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
        addCharacterElement(newPlayerCharacterElement);
        camera.setCharacterAnimation(
            playerCharacterElement.getCharacterAnimation()
        );
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        characterElements.addElement(characterElement);
    }
}

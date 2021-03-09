package org.rpgrunner;

import java.util.Enumeration;
import java.util.Vector;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.event.GameStartEvent;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.graphics.GraphicsRender;
import org.rpgrunner.helper.Camera;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class GameController {
    private final GraphicsRender graphicsRender;
    private final Camera camera;
    private final Vector characterElements;
    private final MapHelper mapHelper;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerMovement playerMovement;

    public GameController(
        final GraphicsRender gameGraphicsRender,
        final Camera gameCamera
    ) {
        graphicsRender = gameGraphicsRender;
        camera = gameCamera;
        characterElements = new Vector(1);
        mapHelper = new MapHelper(this);
        mapHelper.setCharacterElements(characterElements);
    }

    public void executeStartActions(
        final ActionAbstractFactory actionAbstractFactory,
        final GameStartEvent gameStartEvent
    ) {
        gameStartEvent.execute(actionAbstractFactory);
    }

    public void setMap(final Map newMap) {
        map = newMap;

        mapHelper.setMap(map);
        camera.setMap(map);
        graphicsRender.setMap(map);
        removeAllNPCs();

        executeAction(map.getStartAction());
    }

    private void removeAllNPCs() {
        characterElements.removeAllElements();

        if (playerCharacterElement != null) {
            characterElements.addElement(playerCharacterElement);
        }

        graphicsRender.setCharacterElements(characterElements);
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

    public MapHelper getMapHelper() {
        return mapHelper;
    }

    public void setPlayerCharacterElement(
        final CharacterElement newPlayerCharacterElement
    ) {
        removeCharacterElement(playerCharacterElement);
        playerCharacterElement = newPlayerCharacterElement;
        playerMovement = (
            (PlayerMovement) playerCharacterElement.getMovementCommand()
        );
        addCharacterElement(newPlayerCharacterElement);
        camera.setCharacterAnimation(
            playerCharacterElement.getCharacterAnimation()
        );
    }

    private void removeCharacterElement(
        final CharacterElement characterElement
    ) {
        characterElements.removeElement(characterElement);
        graphicsRender.setCharacterElements(characterElements);
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        characterElements.addElement(characterElement);
        graphicsRender.setCharacterElements(characterElements);
    }

    public void executeAction(final Action action) {
        action.execute();
    }

    public void showMessage(final String message) {
        graphicsRender.showMessage(message);
    }
}

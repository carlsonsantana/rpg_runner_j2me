package org.rpgrunner.controller;

import java.util.Enumeration;
import java.util.Vector;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class MapController implements Controller {
    private final MapGraphicsRender mapGraphicsRender;
    private final Vector characterElements;
    private final MapHelper mapHelper;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerMovement playerMovement;

    public MapController(
        final MapGraphicsRender gameGraphicsRender,
        final MapHelper gameMapHelper
    ) {
        characterElements = new Vector(1);
        mapGraphicsRender = gameGraphicsRender;
        mapGraphicsRender.setCharacterElements(characterElements);
        mapHelper = gameMapHelper;
        mapHelper.setCharacterElements(characterElements);
    }

    public void setMap(final Map newMap) {
        map = newMap;

        mapHelper.setMap(map);
        mapGraphicsRender.setMap(map);
        removeAllNPCs();
    }

    private void removeAllNPCs() {
        characterElements.removeAllElements();

        if (playerCharacterElement != null) {
            characterElements.addElement(playerCharacterElement);
        }

        mapGraphicsRender.notifyChangesCharacterElements();
    }

    public Map getMap() {
        return map;
    }

    public void prepareFrameAnimation() {
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

    public void render() {
        mapGraphicsRender.render();
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
        mapGraphicsRender.setCharacterAnimation(
            playerCharacterElement.getCharacterAnimation()
        );
    }

    private void removeCharacterElement(
        final CharacterElement characterElement
    ) {
        characterElements.removeElement(characterElement);
        mapGraphicsRender.notifyChangesCharacterElements();
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        characterElements.addElement(characterElement);
        mapGraphicsRender.notifyChangesCharacterElements();
    }
}

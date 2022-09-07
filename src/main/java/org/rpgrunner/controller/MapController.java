package org.rpgrunner.controller;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class MapController implements Controller {
    private final MapGraphicsRender mapGraphicsRender;
    private final MapHelper mapHelper;
    private final CharacterElement[] characterElements;
    private Map map;
    private CharacterElement playerCharacterElement;
    private PlayerMovement playerMovement;
    private int numberCharacters;

    public MapController(
        final MapGraphicsRender gameGraphicsRender,
        final MapHelper gameMapHelper,
        final CharacterElement[] gameCharacterElements
    ) {
        characterElements = gameCharacterElements;
        numberCharacters = 0;
        mapGraphicsRender = gameGraphicsRender;
        mapHelper = gameMapHelper;
    }

    public void setMap(final Map newMap) {
        map = newMap;

        mapHelper.setMap(map);
        mapGraphicsRender.setMap(map);
        removeAllNPCs();
    }

    private void removeAllNPCs() {
        for (int i = 0; i < numberCharacters; i++) {
            characterElements[i] = null;
        }

        numberCharacters = 0;

        if (playerCharacterElement != null) {
            characterElements[numberCharacters++] = playerCharacterElement;
        }

        mapGraphicsRender.notifyChangesCharacterElements();
    }

    public Map getMap() {
        return map;
    }

    public void prepareFrameAnimation() {
        for (int i = 0; i < numberCharacters; i++) {
            CharacterElement characterElement = characterElements[i];
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
        if (playerCharacterElement != null) {
            removeCharacterElement(playerCharacterElement);
        }

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
        CharacterElement lastCharacterElement = (
            characterElements[--numberCharacters]
        );

        for (int i = 0; i < numberCharacters; i++) {
            if (characterElement == characterElements[i]) {
                characterElements[i] = lastCharacterElement;
            }
        }

        characterElements[numberCharacters] = null;

        mapGraphicsRender.notifyChangesCharacterElements();
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        characterElements[numberCharacters++] = characterElement;
        mapGraphicsRender.notifyChangesCharacterElements();
    }
}

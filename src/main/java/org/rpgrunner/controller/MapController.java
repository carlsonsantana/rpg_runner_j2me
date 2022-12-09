package org.rpgrunner.controller;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class MapController implements Controller {
    private final MapGraphicsRender mapGraphicsRender;
    private final MapHelper mapHelper;
    private final CharacterElement[] characterElements;
    private Map map;
    private CharacterElement playerCharacterElement;
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
            GameCharacter character = characterElement.getCharacterAnimation();

            character.getMovementCommand().execute();
            character.doAnimation();
        }
    }

    public void render() {
        mapGraphicsRender.render();
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
        GameCharacter playerCharacter = (
            playerCharacterElement.getCharacterAnimation()
        );
        addCharacterElement(newPlayerCharacterElement);
        mapGraphicsRender.setCharacterAnimation(playerCharacter);
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

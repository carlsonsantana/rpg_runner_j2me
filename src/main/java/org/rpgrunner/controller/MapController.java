package org.rpgrunner.controller;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;

public class MapController implements Controller {
    private final MapGraphicsRender mapGraphicsRender;
    private final MapHelper mapHelper;
    private final GameCharacter[] characters;
    private Map map;
    private GameCharacter playerCharacter;
    private int numberCharacters;

    public MapController(
        final MapGraphicsRender gameGraphicsRender,
        final MapHelper gameMapHelper,
        final GameCharacter[] gameCharacters
    ) {
        characters = gameCharacters;
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
            characters[i] = null;
        }

        numberCharacters = 0;

        if (playerCharacter != null) {
            characters[numberCharacters++] = playerCharacter;
        }

        mapGraphicsRender.notifyChangesCharacterElements();
    }

    public Map getMap() {
        return map;
    }

    public void prepareFrameAnimation() {
        for (int i = 0; i < numberCharacters; i++) {
            GameCharacter character = characters[i];

            character.getMovementCommand().execute();
            character.doAnimation();
        }
    }

    public void render() {
        mapGraphicsRender.render();
    }

    public GameCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public MapHelper getMapHelper() {
        return mapHelper;
    }

    public void setPlayerCharacter(final GameCharacter newPlayerCharacter) {
        if (playerCharacter != null) {
            removeCharacter(playerCharacter);
        }

        playerCharacter = newPlayerCharacter;
        addCharacter(playerCharacter);
        mapGraphicsRender.setCharacterAnimation(playerCharacter);
    }

    private void removeCharacter(final GameCharacter character) {
        GameCharacter lastCharacter = characters[--numberCharacters];

        for (int i = 0; i < numberCharacters; i++) {
            if (character == characters[i]) {
                characters[i] = lastCharacter;
            }
        }

        characters[numberCharacters] = null;

        mapGraphicsRender.notifyChangesCharacterElements();
    }

    public void addCharacter(final GameCharacter character) {
        characters[numberCharacters++] = character;
        mapGraphicsRender.notifyChangesCharacterElements();
    }
}

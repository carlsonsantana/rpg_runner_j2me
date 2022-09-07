package org.rpgrunner.test.mock.controller;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.helper.MapHelper;
import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.graphics.MapGraphicsRenderSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class MapControllerSpy extends MapController {
    private Map map;
    private int countMapChanged;
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private Action lastAction;
    private String lastMessage;
    private boolean prepareFrameAnimationCalled;
    private boolean renderCalled;

    public MapControllerSpy() {
        super(
            new MapGraphicsRenderSpy(),
            new MapHelperSpy(),
            new CharacterElement[0]
        );
        map = null;
        countMapChanged = 0;
        prepareFrameAnimationCalled = false;
    }

    public void setMap(final Map newMap) {
        map = newMap;
        countMapChanged++;
    }

    public Map getMap() {
        return map;
    }

    public int getCountMapChanged() {
        return countMapChanged;
    }

    public void setPlayerCharacterElement(
        final CharacterElement newPlayerCharacterElement
    ) {
        playerCharacterElement = newPlayerCharacterElement;
    }

    public CharacterElement getPlayerCharacterElement() {
        return playerCharacterElement;
    }

    public MapHelper getMapHelper() {
        return new MapHelperSpy();
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        lastCharacterElementAdded = characterElement;
    }

    public CharacterElement getLastCharacterElementAdded() {
        return lastCharacterElementAdded;
    }

    public void prepareFrameAnimation() {
        prepareFrameAnimationCalled = true;
    }

    public boolean isPrepareFrameAnimationCalled() {
        return prepareFrameAnimationCalled;
    }

    public void render() {
        renderCalled = true;
    }

    public boolean isRenderCalled() {
        return renderCalled;
    }
}

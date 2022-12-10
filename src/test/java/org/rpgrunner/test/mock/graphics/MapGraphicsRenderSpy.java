package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.graphics.MapGraphicsRender;
import org.rpgrunner.map.Map;

public class MapGraphicsRenderSpy extends GraphicsRenderSpy implements
MapGraphicsRender {
    private Map map;
    private GameCharacter character;

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }

    public void notifyChangesCharacters() { }

    public void setCharacter(final GameCharacter newCharacter) {
        character = newCharacter;
    }

    public GameCharacter getCharacter() {
        return character;
    }
}

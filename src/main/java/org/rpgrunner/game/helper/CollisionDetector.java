package org.rpgrunner.game.helper;

import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.map.Map;

public class CollisionDetector {
    private Map map;
    private GameCharacter[] characters;

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }

    public void setCharacters(final GameCharacter[] newCharacters) {
        characters = newCharacters;
    }

    public GameCharacter[] getCharacters() {
        return characters;
    }

    public boolean canMove(final GameCharacter character) {
        return map.canMoveTo(
            character.getMapPositionX(),
            character.getMapPositionY(),
            character.getMapNextPositionX(),
            character.getMapNextPositionY(),
            character.getDirection()
        );
    }
}

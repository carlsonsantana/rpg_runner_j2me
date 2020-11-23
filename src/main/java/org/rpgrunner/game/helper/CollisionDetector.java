package org.rpgrunner.game.helper;

import org.rpgrunner.game.map.Map;

public class CollisionDetector {
    private Map map;

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }
}

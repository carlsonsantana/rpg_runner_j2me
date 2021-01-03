package org.rpgrunner.test.mock;

import org.rpgrunner.GameController;
import org.rpgrunner.map.Map;

public class GameControllerSpy extends GameController {
    private Map map;

    public GameControllerSpy() {
        super(null, null, null, null);
        map = null;
    }

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }
}

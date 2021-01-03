package org.rpgrunner.test.mock;

import org.rpgrunner.GameController;
import org.rpgrunner.map.Map;

public class GameControllerSpy extends GameController {
    private Map map;
    private int countMapChanged;

    public GameControllerSpy() {
        super(null, null, null, null);
        map = null;
        countMapChanged = 0;
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
}

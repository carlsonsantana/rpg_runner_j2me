package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class Teleport implements Action {
    private final GameController gameController;
    private final String mapName;

    public Teleport(
        final GameController currentGameController,
        final String toMapName
    ) {
        gameController = currentGameController;
        mapName = toMapName;
    }

    public void execute() {
        if (isOtherMap()) {
            Map map = MapLoader.loadMap(mapName);
            gameController.setMap(map);
        }
    }

    private boolean isOtherMap() {
        Map map = gameController.getMap();

        return ((map == null) || (!mapName.equals(map.getFileBaseName())));
    }
}

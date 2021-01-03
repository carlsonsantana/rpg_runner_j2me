package org.rpgrunner.event;

import org.rpgrunner.GameController;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class TeleportEvent {
    private GameController gameController;
    private String mapName;

    public TeleportEvent(
        final GameController currentGameController,
        final String toMapName
    ) {
        gameController = currentGameController;
        mapName = toMapName;
    }

    public void call() {
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

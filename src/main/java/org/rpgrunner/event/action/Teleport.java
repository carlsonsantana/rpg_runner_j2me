package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class Teleport implements Action {
    private final GameController gameController;
    private final String mapName;
    private final LocalTeleport localTeleport;

    public Teleport(
        final GameController currentGameController,
        final String toMapName,
        final int toMapPositionX,
        final int toMapPositionY
    ) {
        gameController = currentGameController;
        mapName = toMapName;
        localTeleport = new LocalTeleport(toMapPositionX, toMapPositionY);
    }

    public void execute() {
        if (isOtherMap()) {
            Map map = MapLoader.loadMap(mapName);
            gameController.setMap(map);
        }

        localTeleport.setCharacterElement(
            gameController.getPlayerCharacterElement()
        );
        localTeleport.execute();
    }

    private boolean isOtherMap() {
        Map map = gameController.getMap();

        return ((map == null) || (!mapName.equals(map.getFileBaseName())));
    }
}

package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class Teleport implements Action {
    private final GameController gameController;
    private final String mapName;
    private final LocalTeleport localTeleport;
    private final MapLoader mapLoader;

    public Teleport(
        final GameController currentGameController,
        final MapLoader currentMapLoader,
        final String toMapName,
        final int toMapPositionX,
        final int toMapPositionY
    ) {
        gameController = currentGameController;
        mapLoader = currentMapLoader;
        mapName = toMapName;
        localTeleport = new LocalTeleport(toMapPositionX, toMapPositionY);
    }

    public void execute() {
        loadMap();
        changePlayerCharacterPosition();
    }

    private void loadMap() {
        Map map;

        if (isOtherMap()) {
            map = mapLoader.loadMap(mapName);
        } else {
            map = gameController.getMap();
        }

        gameController.setMap(map);
    }

    private boolean isOtherMap() {
        Map map = gameController.getMap();

        return ((map == null) || (!mapName.equals(map.getFileBaseName())));
    }

    private void changePlayerCharacterPosition() {
        localTeleport.setCharacterElement(
            gameController.getPlayerCharacterElement()
        );
        localTeleport.execute();
    }
}

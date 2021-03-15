package org.rpgrunner.event.action;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class Teleport implements Action {
    private final MapController mapController;
    private final String mapName;
    private final LocalTeleport localTeleport;
    private final MapLoader mapLoader;

    public Teleport(
        final MapController currentMapController,
        final MapLoader currentMapLoader,
        final String toMapName,
        final int toMapPositionX,
        final int toMapPositionY
    ) {
        mapController = currentMapController;
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
            map = mapController.getMap();
        }

        mapController.setMap(map);
    }

    private boolean isOtherMap() {
        Map map = mapController.getMap();

        return ((map == null) || (!mapName.equals(map.getFileBaseName())));
    }

    private void changePlayerCharacterPosition() {
        localTeleport.setCharacterElement(
            mapController.getPlayerCharacterElement()
        );
        localTeleport.execute();
    }
}

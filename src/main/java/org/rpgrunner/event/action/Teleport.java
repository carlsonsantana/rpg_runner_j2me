package org.rpgrunner.event.action;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;

public class Teleport implements Action {
    private final MapController mapController;
    private final byte mapID;
    private final LocalTeleport localTeleport;
    private final MapLoader mapLoader;
    private final ActionQueue actionQueue;

    public Teleport(
        final MapController currentMapController,
        final MapLoader currentMapLoader,
        final ActionQueue currentActionQueue,
        final byte toMapID,
        final int toMapPositionX,
        final int toMapPositionY
    ) {
        mapController = currentMapController;
        mapLoader = currentMapLoader;
        mapID = toMapID;
        actionQueue = currentActionQueue;
        localTeleport = new LocalTeleport(toMapPositionX, toMapPositionY);
    }

    public void execute() {
        loadMap();
        changePlayerCharacterPosition();
    }

    private void loadMap() {
        Map map = getMap();

        mapController.setMap(map);
        actionQueue.push(map.getStartAction());
    }

    private Map getMap() {
        if (isOtherMap()) {
            return mapLoader.loadMap(mapID);
        }

        return mapController.getMap();
    }

    private boolean isOtherMap() {
        Map map = mapController.getMap();

        return ((map == null) || (mapID != map.getID()));
    }

    private void changePlayerCharacterPosition() {
        localTeleport.setCharacterElement(
            mapController.getPlayerCharacterElement()
        );
        localTeleport.execute();
    }
}

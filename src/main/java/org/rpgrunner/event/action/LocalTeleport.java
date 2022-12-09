package org.rpgrunner.event.action;

import org.rpgrunner.character.GameCharacter;

public class LocalTeleport implements Action {
    private final int mapPositionX;
    private final int mapPositionY;
    private GameCharacter character;

    public LocalTeleport(
        final int toMapPositionX,
        final int toMapPositionY
    ) {
        mapPositionX = toMapPositionX;
        mapPositionY = toMapPositionY;
    }

    public void setCharacter(final GameCharacter newCharacter) {
        character = newCharacter;
    }

    public void execute() {
        character.setMapPosition(mapPositionX, mapPositionY);
        character.updateScreenPositionFromMapPosition();
    }
}

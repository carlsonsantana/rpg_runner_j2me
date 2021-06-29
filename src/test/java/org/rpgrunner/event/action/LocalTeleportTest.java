package org.rpgrunner.event.action;

public class LocalTeleportTest extends AbstractLocalTeleportTest {
    protected LocalTeleport createLocalTeleport(
        final int newMapPositionX,
        final int newMapPositionY
    ) {
        return new LocalTeleport(newMapPositionX, newMapPositionY);
    }
}

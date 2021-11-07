package org.rpgrunner.event;

import org.rpgrunner.event.action.Action;

public class MapEventTest extends AbstractMapEventTest {
    protected MapEvent createMapEvent(
        final byte directions,
        final Action action
    ) {
        return new MapEvent(action, directions);
    }
}

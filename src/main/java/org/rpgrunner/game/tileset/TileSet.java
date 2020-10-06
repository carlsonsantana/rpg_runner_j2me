package org.rpgrunner.game.tileset;

import org.rpgrunner.game.Direction;

public class TileSet {
    private static final byte COLLISION_BITS = 4;
    private final String name;
    private final byte[] collisions;

    public TileSet(final String tileSetName, final byte[] tileSetCollisions) {
        name = tileSetName;
        collisions = tileSetCollisions;
    }

    public String getName() {
        return name;
    }

    public boolean canColideOn(
        final int tileIndexFrom,
        final int tileIndexTo,
        final byte direction
    ) {
        return (
            hasCollision(tileIndexFrom, direction)
            || hasCollision(tileIndexTo, Direction.invertDirection(direction))
        );
    }

    private boolean hasCollision(final int tileIndex, final byte direction) {
        boolean firstFourBits = (tileIndex % 2) == 0;
        int collisionsIndex = tileIndex / 2;
        int compareDirection;
        if (firstFourBits) {
            compareDirection = direction << COLLISION_BITS;
        } else {
            compareDirection = direction;
        }

        return ((collisions[collisionsIndex] & compareDirection) != 0);
    }
}

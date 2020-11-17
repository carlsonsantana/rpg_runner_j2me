package org.rpgrunner.game.tileset;

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

    public boolean canCollideOn(final int tileIndex, final byte direction) {
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

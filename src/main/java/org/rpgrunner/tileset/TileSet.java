package org.rpgrunner.tileset;

public class TileSet {
    private static final byte COLLISION_BITS = 4;
    private static final byte LAST_BITS_COMPARE = 15;
    private final String name;
    private final byte[] collisions;

    public TileSet(final String tileSetName, final byte[] tileSetCollisions) {
        name = tileSetName;
        collisions = tileSetCollisions;
    }

    public String getName() {
        return name;
    }

    public boolean canPassOn(final int tileIndex, final byte direction) {
        byte collision = getCollision(tileIndex);

        return ((collision & direction) == 0);
    }

    private byte getCollision(final int tileIndex) {
        boolean firstFourBits = (tileIndex % 2) == 0;
        int collisionsIndex = tileIndex / 2;

        if (firstFourBits) {
            return (byte) (collisions[collisionsIndex] >> COLLISION_BITS);
        } else {
            return (byte) (collisions[collisionsIndex] & LAST_BITS_COMPARE);
        }
    }
}

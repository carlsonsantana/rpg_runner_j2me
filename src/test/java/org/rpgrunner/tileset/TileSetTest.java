package org.rpgrunner.tileset;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;

public class TileSetTest extends TestCase {
    private static final byte[] ALL_POSSIBLE_COLLISIONS = new byte[] {
        0,
        Direction.UP,
        Direction.UP | Direction.RIGHT,
        Direction.UP | Direction.RIGHT | Direction.DOWN,
        Direction.UP | Direction.RIGHT | Direction.DOWN | Direction.LEFT,
        Direction.UP | Direction.RIGHT | Direction.LEFT,
        Direction.UP | Direction.DOWN,
        Direction.UP | Direction.DOWN | Direction.LEFT,
        Direction.UP | Direction.LEFT,
        Direction.RIGHT,
        Direction.RIGHT | Direction.DOWN,
        Direction.RIGHT | Direction.DOWN | Direction.LEFT,
        Direction.RIGHT | Direction.LEFT,
        Direction.DOWN,
        Direction.DOWN | Direction.LEFT,
        Direction.LEFT
    };

    private static final int[] ALL_POSSIBLE_UP_COLLISIONS = new int[] {
        1, 2, 3, 4, 5, 6, 7, 8
    };

    private static final int[] ALL_POSSIBLE_RIGHT_COLLISIONS = new int[] {
        2, 3, 4, 5, 9, 10, 11, 12
    };

    private static final int[] ALL_POSSIBLE_DOWN_COLLISIONS = new int[] {
        3, 4, 6, 7, 10, 11, 13, 14
    };

    private static final int[] ALL_POSSIBLE_LEFT_COLLISIONS = new int[] {
        4, 5, 7, 8, 11, 12, 14, 15
    };
    private static final byte COLLISION_BITS = 4;
    private final Random random;
    private byte tileSetID;
    private byte[] collisions;
    private TileSet tileSet;

    public TileSetTest() {
        random = new Random();
    }

    public void setUp() {
        tileSetID = (byte) random.nextInt(Byte.MAX_VALUE);
        collisions = generateCollisions();
        tileSet = new TileSet(tileSetID, collisions);
    }

    private byte[] generateCollisions() {
        byte[] newCollisions = new byte[ALL_POSSIBLE_COLLISIONS.length / 2];

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if ((i % 2) == 0) {
                newCollisions[i / 2] = (
                    (byte) (ALL_POSSIBLE_COLLISIONS[i] << COLLISION_BITS)
                );
            } else {
                newCollisions[i / 2] = (
                    (byte) (newCollisions[i / 2] | ALL_POSSIBLE_COLLISIONS[i])
                );
            }
        }

        return newCollisions;
    }

    public void testReturnSameName() {
        Assert.assertEquals(tileSetID, tileSet.getID());
    }

    public void testCollisionUp() {
        checkCollisionDirection(Direction.UP, ALL_POSSIBLE_UP_COLLISIONS);
    }

    public void testCollisionRight() {
        checkCollisionDirection(Direction.RIGHT, ALL_POSSIBLE_RIGHT_COLLISIONS);
    }

    public void testCollisionDown() {
        checkCollisionDirection(Direction.DOWN, ALL_POSSIBLE_DOWN_COLLISIONS);
    }

    public void testCollisionLeft() {
        checkCollisionDirection(Direction.LEFT, ALL_POSSIBLE_LEFT_COLLISIONS);
    }

    private void checkCollisionDirection(
        final byte direction,
        final int[] allDirectionCollisions
    ) {
        int indexDirectionCollisions = 0;

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if (
                (indexDirectionCollisions < allDirectionCollisions.length)
                && (i == allDirectionCollisions[indexDirectionCollisions])
            ) {
                Assert.assertFalse(tileSet.canPassOn(i, direction));
                indexDirectionCollisions++;
            } else {
                Assert.assertTrue(tileSet.canPassOn(i, direction));
            }
        }
    }
}

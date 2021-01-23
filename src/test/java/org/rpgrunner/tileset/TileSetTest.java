package org.rpgrunner.tileset;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;

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

    public void testReturnSameName() {
        String randomName = RandomGenerator.getRandomString();
        TileSet tileSet = new TileSet(randomName, null);
        Assert.assertEquals(randomName, tileSet.getName());
    }

    public void testCollisionUp() {
        byte[] collisions = generateCollisions();
        TileSet tileSet = new TileSet(null, collisions);

        int indexDirectionCollisions = 0;

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if (
                (
                    indexDirectionCollisions
                    < ALL_POSSIBLE_UP_COLLISIONS.length
                )
                && (i == ALL_POSSIBLE_UP_COLLISIONS[indexDirectionCollisions])
            ) {
                Assert.assertFalse(tileSet.canPassOn(i, Direction.UP));
                indexDirectionCollisions++;
            } else {
                Assert.assertTrue(tileSet.canPassOn(i, Direction.UP));
            }
        }
    }

    public void testCollisionRight() {
        byte[] collisions = generateCollisions();
        TileSet tileSet = new TileSet(null, collisions);

        int indexDirectionCollisions = 0;

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if (
                (
                    indexDirectionCollisions
                    < ALL_POSSIBLE_RIGHT_COLLISIONS.length
                )
                && (
                    i == ALL_POSSIBLE_RIGHT_COLLISIONS[indexDirectionCollisions]
                )
            ) {
                Assert.assertFalse(tileSet.canPassOn(i, Direction.RIGHT));
                indexDirectionCollisions++;
            } else {
                Assert.assertTrue(tileSet.canPassOn(i, Direction.RIGHT));
            }
        }
    }

    public void testCollisionDown() {
        byte[] collisions = generateCollisions();
        TileSet tileSet = new TileSet(null, collisions);

        int indexDirectionCollisions = 0;

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if (
                (
                    indexDirectionCollisions
                    < ALL_POSSIBLE_DOWN_COLLISIONS.length
                )
                && (i == ALL_POSSIBLE_DOWN_COLLISIONS[indexDirectionCollisions])
            ) {
                Assert.assertFalse(tileSet.canPassOn(i, Direction.DOWN));
                indexDirectionCollisions++;
            } else {
                Assert.assertTrue(tileSet.canPassOn(i, Direction.DOWN));
            }
        }
    }

    public void testCollisionLeft() {
        byte[] collisions = generateCollisions();
        TileSet tileSet = new TileSet(null, collisions);

        int indexDirectionCollisions = 0;

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if (
                (
                    indexDirectionCollisions
                    < ALL_POSSIBLE_LEFT_COLLISIONS.length
                )
                && (i == ALL_POSSIBLE_LEFT_COLLISIONS[indexDirectionCollisions])
            ) {
                Assert.assertFalse(tileSet.canPassOn(i, Direction.LEFT));
                indexDirectionCollisions++;
            } else {
                Assert.assertTrue(tileSet.canPassOn(i, Direction.LEFT));
            }
        }
    }

    private byte[] generateCollisions() {
        byte[] collisions = new byte[ALL_POSSIBLE_COLLISIONS.length / 2];

        for (int i = 0; i < ALL_POSSIBLE_COLLISIONS.length; i++) {
            if ((i % 2) == 0) {
                collisions[i / 2] = (byte) (ALL_POSSIBLE_COLLISIONS[i] << 4);
            } else {
                collisions[i / 2] = (byte) (
                    collisions[i / 2]
                    | ALL_POSSIBLE_COLLISIONS[i]
                );
            }
        }

        return collisions;
    }
}

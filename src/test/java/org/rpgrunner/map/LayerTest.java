package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.TileSetSpy;

public class LayerTest extends TestCase {
    private static int TEST_REPEAT_LOOP = 100;
    private static int TILE_SET_RESULTS_SIZE = 2 * TEST_REPEAT_LOOP;
    private Random random;
    private byte[][] tileMap;
    private boolean[] tileSetResults;
    private TileSetSpy tileSetSpy;
    private Layer layer;

    public LayerTest() {
        random = new Random();
    }

    public void setUp() {
        tileSetResults = generateTileSetResults();
        tileSetSpy = new TileSetSpy(tileSetResults);
        tileMap = generateRandomTileMap();
        layer = new Layer(tileSetSpy, tileMap);
    }

    private boolean[] generateTileSetResults() {
        boolean[] newTileSetResults = new boolean[TILE_SET_RESULTS_SIZE];

        for (int i = 0; i < TILE_SET_RESULTS_SIZE; i++) {
            newTileSetResults[i] = true;
        }

        return newTileSetResults;
    }

    private byte[][] generateRandomTileMap() {
        int height = random.nextInt(100) + 2;
        int width = random.nextInt(100) + 2;
        byte[][] newTileMap = new byte[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newTileMap[y][x] = (byte) random.nextInt(256);
            }
        }

        return newTileMap;
    }

    public void testReturnSameTileMap() {
        Assert.assertSame(tileMap, layer.getTileMap());
    }

    public void testReturnSameWidthOfTileMap() {
        Assert.assertEquals(tileMap[0].length, layer.getWidth());
    }

    public void testReturnSameHeightOfTileMap() {
        Assert.assertEquals(tileMap.length, layer.getHeight());
    }

    public void testCanMoveToValidPositionsWithoutCollisionsLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveToValidPositionsWithoutCollisions();
        }
    }

    private void checkCanMoveToValidPositionsWithoutCollisions() {
        checkMoveToValidPositionsWithoutCollisions(true);
    }

    public void testCantMoveToNegativePositions() {
        Assert.assertFalse(layer.canMoveTo(0, 0, -1, 0, Direction.LEFT));
        Assert.assertFalse(layer.canMoveTo(0, 0, 0, -1, Direction.UP));
    }

    public void testCantMoveToPositionOffTheMap() {
        int borderX = layer.getWidth() - 1;
        int borderY = layer.getHeight() - 1;
        Assert.assertFalse(
            layer.canMoveTo(
                borderX,
                borderY,
                borderX + 1,
                borderY,
                Direction.RIGHT
            )
        );
        Assert.assertFalse(
            layer.canMoveTo(
                borderX,
                borderY,
                borderX,
                borderY + 1,
                Direction.DOWN
            )
        );
    }

    public void testCantMoveToPositionWhenHasCollisionLoop() {
        forceTileSetCollision();

        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveToValidPositionsWithoutCollisions();
        }
    }

    private void forceTileSetCollision() {
        for (int i = 0; i < TILE_SET_RESULTS_SIZE; i++) {
            tileSetResults[i] = false;
        }
    }

    private void checkCantMoveToValidPositionsWithoutCollisions() {
        checkMoveToValidPositionsWithoutCollisions(false);
    }

    private void checkMoveToValidPositionsWithoutCollisions(
        boolean expectedValue
    ) {
        int maxX = layer.getWidth();
        int maxY = layer.getHeight();
        int randomValidX = random.nextInt(maxX);
        int randomValidY = random.nextInt(maxY);
        byte direction;
        int targetX;
        int targetY;
        boolean invalidPosition;

        do {
            direction = getRandomDirection();
            targetX = randomValidX;
            targetY = randomValidY;

            if (direction == Direction.UP) {
                targetY--;
            } else if (direction == Direction.RIGHT) {
                targetX++;
            } else if (direction == Direction.DOWN) {
                targetY++;
            } else {
                targetX--;
            }

            invalidPosition = (
                (targetX < 0)
                || (targetX >= maxX)
                || (targetY < 0)
                || (targetY >= maxY)
            );
        } while (invalidPosition);

        Assert.assertEquals(
            expectedValue,
            layer.canMoveTo(
                randomValidX,
                randomValidY,
                targetX,
                targetY,
                direction
            )
        );
    }

    private byte getRandomDirection() {
        int randomNumber = random.nextInt(4);

        if (randomNumber == 0) {
            return Direction.UP;
        }

        if (randomNumber == 1) {
            return Direction.RIGHT;
        }

        if (randomNumber == 2) {
            return Direction.DOWN;
        }

        return Direction.LEFT;
    }
}

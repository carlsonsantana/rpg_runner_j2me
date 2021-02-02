package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.TileSetSpy;

public class LayerTest extends TestCase {
    private byte[][] tileMap;
    private boolean[] tileSetResults;
    private TileSetSpy tileSetSpy;
    private Layer layer;

    public void setUp() {
        tileSetResults = generateTileSetResults();
        tileSetSpy = new TileSetSpy(tileSetResults);
        tileMap = generateRandomTileMap();
        layer = new Layer(tileSetSpy, tileMap);
    }

    private boolean[] generateTileSetResults() {
        boolean[] newTileSetResults = new boolean[100];

        for (int i = 0; i < 100; i++) {
            newTileSetResults[i] = true;
        }

        return newTileSetResults;
    }

    private byte[][] generateRandomTileMap() {
        Random random = new Random();
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

    public void testCanMoveToValidPositionsWithoutCollisions() {
        boolean[] results = getTestsResultsValidPositions(layer);

        for (int i = 0; i < results.length; i++) {
            Assert.assertTrue(results[i]);
        }
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

    public void testCantMoveToPositionWhenHasCollision() {
        forceTileSetCollision();
        boolean[] results = getTestsResultsValidPositions(layer);

        for (int i = 0; i < results.length; i++) {
            Assert.assertFalse(results[i]);
        }
    }

    private void forceTileSetCollision() {
        for (int i = 0; i < 100; i++) {
            tileSetResults[i] = false;
        }
    }

    private boolean[] getTestsResultsValidPositions(final Layer layer) {
        boolean[] results = new boolean[8];
        int index = 0;
        results[index++] = layer.canMoveTo(1, 0, 0, 0, Direction.LEFT);
        results[index++] = layer.canMoveTo(0, 1, 0, 0, Direction.UP);
        results[index++] = layer.canMoveTo(0, 0, 1, 0, Direction.RIGHT);
        results[index++] = layer.canMoveTo(0, 0, 0, 1, Direction.DOWN);

        int borderX = layer.getWidth() - 1;
        int borderY = layer.getHeight() - 1;
        results[index++] = layer.canMoveTo(
            borderX,
            borderY,
            borderX - 1,
            borderY,
            Direction.LEFT
        );
        results[index++] = layer.canMoveTo(
            borderX,
            borderY,
            borderX,
            borderY - 1,
            Direction.UP
        );
        results[index++] = layer.canMoveTo(
            borderX - 1,
            borderY,
            borderX,
            borderY,
            Direction.RIGHT
        );
        results[index++] = layer.canMoveTo(
            borderX,
            borderY - 1,
            borderX,
            borderY,
            Direction.DOWN
        );

        return results;
    }
}

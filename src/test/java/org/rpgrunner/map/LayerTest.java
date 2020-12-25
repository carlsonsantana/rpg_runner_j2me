package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.mock.TileSetSpy;

public class LayerTest extends TestCase {
    private byte[][] tileMap;

    public void setUp() {
        tileMap = generateRandomTileMap();
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
        Layer layer = new Layer(null, tileMap);
        Assert.assertSame(tileMap, layer.getTileMap());
    }

    public void testReturnSameWidthOfTileMap() {
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap[0].length, layer.getWidth());
    }

    public void testReturnSameHeightOfTileMap() {
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap.length, layer.getHeight());
    }

    public void testCanMoveToValidPositionsWithoutCollisions() {
        boolean[] tileSetResults = new boolean[] {
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, true, true, true
        };
        TileSetSpy tileSetSpy = new TileSetSpy(tileSetResults);

        Layer layer = new Layer(tileSetSpy, tileMap);
        boolean[] results = getTestsResultsValidPositions(layer);

        for (int i = 0; i < results.length; i++) {
            Assert.assertTrue(results[i]);
        }
    }

    public void testCantMoveToNegativePositions() {
        boolean[] results = new boolean[] {true, true, true, true};
        TileSetSpy tileSetSpy = new TileSetSpy(results);

        Layer layer = new Layer(tileSetSpy, tileMap);
        Assert.assertFalse(layer.canMoveTo(0, 0, -1, 0, Direction.LEFT));
        Assert.assertFalse(layer.canMoveTo(0, 0, 0, -1, Direction.UP));
    }

    public void testCantMoveToPositionOffTheMap() {
        boolean[] results = new boolean[] {true, true, true, true};
        TileSetSpy tileSetSpy = new TileSetSpy(results);

        Layer layer = new Layer(tileSetSpy, tileMap);
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
        boolean[] tileSetResults = new boolean[] {
            false, false, false, false,
            false, false, false, false
        };
        TileSetSpy tileSetSpy = new TileSetSpy(tileSetResults);

        Layer layer = new Layer(tileSetSpy, tileMap);
        boolean[] results = getTestsResultsValidPositions(layer);

        for (int i = 0; i < results.length; i++) {
            Assert.assertFalse(results[i]);
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

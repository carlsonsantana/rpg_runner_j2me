package org.rpgrunner.game.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.game.Direction;
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
        Assert.assertEquals(tileMap, layer.getTileMap());
    }

    public void testReturnSameWidthOfTileMap() {
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap[0].length, layer.getWidth());
    }

    public void testReturnSameHeightOfTileMap() {
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap.length, layer.getHeight());
    }

    public void testCantMoveToNegativePositions() {
        boolean[] results = new boolean[] {false, false, false, false};
        TileSetSpy tileSetSpy = new TileSetSpy(results);

        Layer layerNegativeX = new Layer(tileSetSpy, tileMap);
        Assert.assertFalse(
            layerNegativeX.canMoveTo(0, 0, -1, 0, Direction.LEFT)
        );

        Layer layerNegativeY = new Layer(tileSetSpy, tileMap);
        Assert.assertFalse(layerNegativeY.canMoveTo(0, 0, 0, -1, Direction.UP));
    }
}

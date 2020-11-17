package org.rpgrunner.game.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LayerTest extends TestCase {
    public void testReturnSameTileMap() {
        byte[][] tileMap = generateRandomTileMap();
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap, layer.getTileMap());
    }

    public void testReturnSameWidthOfTileMap() {
        byte[][] tileMap = generateRandomTileMap();
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap[0].length, layer.getWidth());
    }

    public void testReturnSameHeightOfTileMap() {
        byte[][] tileMap = generateRandomTileMap();
        Layer layer = new Layer(null, tileMap);
        Assert.assertEquals(tileMap.length, layer.getHeight());
    }

    private byte[][] generateRandomTileMap() {
        Random random = new Random();
        int height = random.nextInt(100) + 2;
        int width = random.nextInt(100) + 2;
        byte[][] tileMap = new byte[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tileMap[y][x] = (byte) random.nextInt(256);
            }
        }
        return tileMap;
    }
}

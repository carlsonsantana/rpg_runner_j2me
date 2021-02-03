package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.LayerSpy;

public class MapTest extends TestCase {
    private Random random;
    private Map map;
    private String mapFileBaseName;
    private LayerSpy layerBackground;
    private LayerSpy layerObjects;
    private Layer[] layers;

    public MapTest() {
        random = new Random();
    }

    public void setUp() {
        layerBackground = new LayerSpy();
        layerObjects = new LayerSpy();
        layers = new Layer[] {layerBackground, layerObjects};
        mapFileBaseName = RandomGenerator.getRandomString();
        map = new Map(mapFileBaseName, layers);
    }

    public void testReturnSame() {
        Assert.assertSame(mapFileBaseName, map.getFileBaseName());
    }

    public void testReturnSameWidthLayers() {
        for (int i = 0; i < 100; i++) {
            int width = random.nextInt(255);

            layerBackground.setWidth(width);
            layerObjects.setWidth(width);

            Assert.assertEquals(width, map.getWidth());
        }
    }

    public void testReturnSameHeightLayers() {
        for (int i = 0; i < 100; i++) {
            int height = random.nextInt(255);

            layerBackground.setHeight(height);
            layerObjects.setHeight(height);

            Assert.assertEquals(height, map.getHeight());
        }
    }

    public void testReturnSameLayers() {
        Assert.assertSame(layers, map.getLayers());
    }

    public void testCanMoveToPositionWhenAllLayersAllMoveToPosition() {
        for (int i = 0; i < 100; i++) {
            boolean canMoveToBackground = random.nextInt(2) == 1;
            boolean canMoveToObjects = random.nextInt(2) == 1;

            layerBackground.setCanMove(canMoveToBackground);
            layerObjects.setCanMove(canMoveToObjects);

            boolean canMove = canMoveToBackground && canMoveToObjects;
            int fromX = random.nextInt(100);
            int fromY = random.nextInt(100);
            int toX = random.nextInt(100);
            int toY = random.nextInt(100);
            byte direction = Direction.LEFT;

            Assert.assertEquals(
                canMove,
                map.canMoveTo(fromX, fromY, toX, toY, direction)
            );
        }
    }
}

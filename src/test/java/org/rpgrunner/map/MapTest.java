package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.LayerSpy;

public class MapTest extends TestCase {
    private static int TEST_REPEAT_LOOP = 100;
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

    public void testReturnSameMapFileBaseName() {
        Assert.assertSame(mapFileBaseName, map.getFileBaseName());
    }

    public void testReturnSameWidthLayersLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkReturnSameWidthLayers();
        }
    }

    private void checkReturnSameWidthLayers() {
        int width = random.nextInt(255);

        layerBackground.setWidth(width);
        layerObjects.setWidth(width);

        Assert.assertEquals(width, map.getWidth());
    }

    public void testReturnSameHeightLayersLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkReturnSameHeightLayers();
        }
    }

    private void checkReturnSameHeightLayers() {
        int height = random.nextInt(255);

        layerBackground.setHeight(height);
        layerObjects.setHeight(height);

        Assert.assertEquals(height, map.getHeight());
    }

    public void testReturnSameLayers() {
        Assert.assertSame(layers, map.getLayers());
    }

    public void testCanMoveToPositionWhenAllLayersAllMoveToPositionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveToPositionWhenAllLayersAllMoveToPosition();
        }
    }

    private void checkCanMoveToPositionWhenAllLayersAllMoveToPosition() {
        boolean canMoveToBackground = random.nextInt(2) == 1;
        boolean canMoveToObjects = random.nextInt(2) == 1;
        boolean canMove = canMoveToBackground && canMoveToObjects;
        int fromX = random.nextInt(100);
        int fromY = random.nextInt(100);
        int toX = random.nextInt(100);
        int toY = random.nextInt(100);
        byte direction = Direction.LEFT;

        layerBackground.setCanMove(canMoveToBackground);
        layerObjects.setCanMove(canMoveToObjects);

        Assert.assertEquals(
            canMove,
            map.canMoveTo(fromX, fromY, toX, toY, direction)
        );
    }
}

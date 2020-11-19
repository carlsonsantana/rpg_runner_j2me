package org.rpgrunner.game.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.LayerSpy;

public class MapTest extends TestCase {
    private Map map;
    private LayerSpy layerBackground;
    private LayerSpy layerObjects;

    public void setUp() {
        layerBackground = new LayerSpy();
        layerObjects = new LayerSpy();
        Layer[] layers = new Layer[] {layerBackground, layerObjects};
        map = new Map(layers);
    }

    public void testReturnSameWidthLayers() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int width = random.nextInt(255);

            layerBackground.setWidth(width);
            layerObjects.setWidth(width);

            Assert.assertEquals(width, map.getWidth());
        }
    }

    public void testReturnSameHeightLayers() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int height = random.nextInt(255);

            layerBackground.setHeight(height);
            layerObjects.setHeight(height);

            Assert.assertEquals(height, map.getHeight());
        }
    }
}

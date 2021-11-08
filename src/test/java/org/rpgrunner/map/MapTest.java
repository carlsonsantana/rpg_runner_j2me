package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.MapEventArea;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.event.action.ActionSpy;
import org.rpgrunner.test.mock.map.LayerSpy;

public class MapTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MAXIMUM_MAP_EVENT_AREAS = 100;
    private static final int MAXIMUM_LAYER_WIDTH = 100;
    private static final int MAXIMUM_LAYER_HEIGHT = 100;
    private static final int MAXIMUM_POSITION = 100;
    private Random random;
    private Map map;
    private String mapFileBaseName;
    private LayerSpy layerBackground;
    private LayerSpy layerObjects;
    private Layer[] layers;
    private ActionSpy expectedAction;
    private MapEventArea[] expectedMapEventAreas;

    public MapTest() {
        random = new Random();
    }

    public void setUp() {
        layerBackground = new LayerSpy();
        layerObjects = new LayerSpy();
        mapFileBaseName = RandomGenerator.getRandomString();
        layers = new Layer[] {layerBackground, layerObjects};
        expectedAction = new ActionSpy();
        int sizeMapEventAreas = random.nextInt(MAXIMUM_MAP_EVENT_AREAS);
        expectedMapEventAreas = new MapEventArea[sizeMapEventAreas];
        map = new Map(
            mapFileBaseName,
            layers,
            expectedAction,
            expectedMapEventAreas
        );
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
        int width = random.nextInt(MAXIMUM_LAYER_WIDTH);

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
        int height = random.nextInt(MAXIMUM_LAYER_HEIGHT);

        layerBackground.setHeight(height);
        layerObjects.setHeight(height);

        Assert.assertEquals(height, map.getHeight());
    }

    public void testReturnSameLayers() {
        Assert.assertSame(layers, map.getLayers());
    }

    public void testCanMoveToPositionWhenAllLayersAllowMoveToPositionLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveToPositionWhenAllLayersAllowMoveToPosition();
        }
    }

    private void checkCanMoveToPositionWhenAllLayersAllowMoveToPosition() {
        boolean canMoveToBackground = random.nextInt(2) == 1;
        boolean canMoveToObjects = random.nextInt(2) == 1;
        boolean canMove = canMoveToBackground && canMoveToObjects;

        CharacterSpy character = new CharacterSpy(null);
        int x = random.nextInt(MAXIMUM_POSITION);
        int y = random.nextInt(MAXIMUM_POSITION);
        byte direction = RandomGenerator.getRandomDirection();
        character.setMapPosition(x, y);
        character.setDirection(direction);

        layerBackground.setCanMove(canMoveToBackground);
        layerObjects.setCanMove(canMoveToObjects);

        Assert.assertEquals(canMove, map.canMove(character));
    }

    public void testGetSameStartAction() {
        Action action = map.getStartAction();

        Assert.assertSame(expectedAction, action);
    }

    public void testGetSameMapEventAreas() {
        MapEventArea[] mapEventAreas = map.getMapEventAreas();

        Assert.assertSame(expectedMapEventAreas, mapEventAreas);
    }
}

package org.rpgrunner.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;
import org.rpgrunner.test.mock.map.MapSpy;

public class CameraTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int MINIMUM_SCREEN_WIDTH = 10;
    private static final int MINIMUM_SCREEN_HEIGHT = 10;
    private static final int MAXIMUM_SCREEN_WIDTH = 10;
    private static final int MAXIMUM_SCREEN_HEIGHT = 10;
    private static final int MINIMUM_MAP_WIDTH = 20;
    private static final int MINIMUM_MAP_HEIGHT = 20;
    private static final int MAXIMUM_MAP_WIDTH = 100;
    private static final int MAXIMUM_MAP_HEIGHT = 100;
    private static final int TILE_SIZE = 16;
    private static final int TILE_MIDDLE = TILE_SIZE / 2;
    private Random random;
    private CharacterAnimationSpy characterAnimation;

    public CameraTest() {
        random = new Random();
    }

    public void setUp() {
        characterAnimation = new CharacterAnimationSpy();
    }

    public void testReturnSameScreenWidthAndHeightLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkReturnSameScreenWidthAndHeight();
        }
    }

    private void checkReturnSameScreenWidthAndHeight() {
        int randomWidth = random.nextInt(MAXIMUM_SCREEN_WIDTH);
        int randomHeight = random.nextInt(MAXIMUM_SCREEN_HEIGHT);
        Camera camera = new Camera(randomWidth, randomHeight);
        Assert.assertEquals(randomWidth, camera.getScreenWidth());
        Assert.assertEquals(randomHeight, camera.getScreenHeight());
    }

    public void testReturnZerosForInitialCameraPositions() {
        Map newMap = generateMap();
        Camera camera = generateCamera(newMap);

        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testReturnZerosForCharacterOnTopRightCorner() {
        Map newMap = generateMap();
        Camera camera = generateCamera(newMap);
        characterAnimation.setScreenPosition(0, 0);

        camera.centerCamera();

        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testCenterCameraOnceLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCenterCameraOnce();
        }
    }

    private void checkCenterCameraOnce() {
        Map newMap = generateMap();
        Camera camera = generateCamera(newMap);
        checkCenterCamera(camera, newMap);
    }

    public void testCenterCameraTwiceLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCenterCameraTwice();
        }
    }

    private void checkCenterCameraTwice() {
        Map newMap = generateMap();
        Camera camera = generateCamera(newMap);
        centerCameraOnCharacterOnRandomPosition(camera, newMap);
        checkCenterCamera(camera, newMap);
    }

    private Map generateMap() {
        MapSpy newMap = new MapSpy();
        int randomMapWidth = (
            random.nextInt(MAXIMUM_MAP_WIDTH)
            + MINIMUM_MAP_WIDTH
        );
        int randomMapHeight = (
            random.nextInt(MAXIMUM_MAP_HEIGHT)
            + MINIMUM_MAP_HEIGHT
        );

        newMap.setWidth(randomMapWidth);
        newMap.setHeight(randomMapHeight);

        return newMap;
    }

    private Camera generateCamera(final Map newMap) {
        int randomScreenWidth = (
            (random.nextInt(MAXIMUM_SCREEN_WIDTH) + MINIMUM_SCREEN_WIDTH)
            * TILE_SIZE
        );
        int randomScreenHeight = (
            (random.nextInt(MAXIMUM_SCREEN_HEIGHT) + MINIMUM_SCREEN_HEIGHT)
            * TILE_SIZE
        );

        Camera camera = new Camera(randomScreenWidth, randomScreenHeight);
        camera.setMap(newMap);
        camera.setCharacterAnimation(characterAnimation);

        return camera;
    }

    private void centerCameraOnCharacterOnRandomPosition(
        final Camera camera,
        final Map newMap
    ) {
        int mapWidthPixels = newMap.getWidth() * TILE_SIZE;
        int mapHeightPixels = newMap.getHeight() * TILE_SIZE;
        int randomCharacterPositionX = random.nextInt(mapWidthPixels);
        int randomCharacterPositionY = random.nextInt(mapHeightPixels);
        characterAnimation.setScreenPosition(
            randomCharacterPositionX,
            randomCharacterPositionY
        );
        camera.centerCamera();
    }

    private void checkCenterCamera(final Camera camera, final Map newMap) {
        int mapWidthPixels = newMap.getWidth() * TILE_SIZE;
        int mapHeightPixels = newMap.getHeight() * TILE_SIZE;
        int screenWidth = camera.getScreenWidth();
        int screenHeight = camera.getScreenHeight();
        int middleScreenWidth = screenWidth / 2;
        int middleScreenHeight = screenHeight / 2;
        int maxCameraPositionX = mapWidthPixels - screenWidth;
        int maxCameraPositionY = mapHeightPixels - screenHeight;

        int randomCharacterPositionX = random.nextInt(mapWidthPixels);
        int randomCharacterPositionY = random.nextInt(mapHeightPixels);

        characterAnimation.setScreenPosition(
            randomCharacterPositionX,
            randomCharacterPositionY
        );
        camera.centerCamera();

        int characterScreenPositionX = Math.min(
            Math.max(
                randomCharacterPositionX - middleScreenWidth + TILE_MIDDLE,
                0
            ),
            maxCameraPositionX
        );
        int characterScreenPositionY = Math.min(
            Math.max(randomCharacterPositionY - middleScreenHeight, 0),
            maxCameraPositionY
        );

        Assert.assertEquals(characterScreenPositionX, camera.getX());
        Assert.assertEquals(characterScreenPositionY, camera.getY());
    }
}

package org.rpgrunner.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.map.Map;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.MapSpy;

public class CameraTest extends TestCase {
    private Random random;
    private CharacterAnimationSpy characterAnimation;
    private MapSpy map;

    public CameraTest() {
        random = new Random();
    }

    public void setUp() {
        map = new MapSpy();
        map.setWidth(20);
        map.setHeight(20);

        characterAnimation = new CharacterAnimationSpy();
    }

    public void testReturnSameScreenWidthAndHeight() {
        for (int i = 0; i < 100; i++) {
            int randomWidth = random.nextInt(100);
            int randomHeight = random.nextInt(100);
            Camera camera = new Camera(randomWidth, randomHeight);
            Assert.assertEquals(randomWidth, camera.getScreenWidth());
            Assert.assertEquals(randomHeight, camera.getScreenHeight());
        }
    }

    public void testReturnZerosForInitialCameraPositions() {
        int randomWidth = random.nextInt(100);
        int randomHeight = random.nextInt(100);
        Camera camera = new Camera(randomWidth, randomHeight);

        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testReturnZerosForCharacterOnTopRightCorner() {
        int randomWidth = random.nextInt(100) + 16;
        int randomHeight = random.nextInt(100) + 16;
        Camera camera = new Camera(randomWidth, randomHeight);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();
        characterAnimation.setScreenPosition(0, 0);

        camera.centerCamera();

        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testCenterCameraHorizontalMiddle() {
        Camera camera = new Camera(160, 160);
        characterAnimation.setScreenPosition(160, 0);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();

        Assert.assertEquals(88, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testCenterCameraHorizontalCorners() {
        Camera camera = new Camera(160, 160);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);

        characterAnimation.setScreenPosition(304, 0);

        camera.centerCamera();
        Assert.assertEquals(160, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testCenterCameraVerticalMiddle() {
        Camera camera = new Camera(160, 160);
        characterAnimation.setScreenPosition(0, 160);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();

        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(80, camera.getY());
    }

    public void testCenterCameraVerticalCorners() {
        Camera camera = new Camera(160, 160);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);

        characterAnimation.setScreenPosition(0, 304);

        camera.centerCamera();
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(160, camera.getY());
    }

    public void testCenterCameraOnMiddle() {
        for (int i = 0; i < 1000; i++) {
            Map newMap = generateMap();
            Camera camera = generateCamera(newMap);
            checkCenterCameraOnMiddleOnce(camera, newMap);
        }
    }

    public void testCenterCameraOnMiddleAfterCenterCameraBefore() {
        for (int i = 0; i < 1000; i++) {
            Map newMap = generateMap();
            Camera camera = generateCamera(newMap);
            centerCameraOnCharacterOnRandomPosition(camera, newMap);
            checkCenterCameraOnMiddleOnce(camera, newMap);
        }
    }

    private Map generateMap() {
        MapSpy newMap = new MapSpy();
        int randomMapWidth = random.nextInt(100) + 20;
        int randomMapHeight = random.nextInt(100) + 20;

        newMap.setWidth(randomMapWidth);
        newMap.setHeight(randomMapHeight);

        return newMap;
    }

    private Camera generateCamera(final Map newMap) {
        int randomScreenWidth = (random.nextInt(10) + 2) * 16;
        int randomScreenHeight = (random.nextInt(10) + 2) * 16;

        Camera camera = new Camera(randomScreenWidth, randomScreenHeight);
        camera.setMap(newMap);
        camera.setCharacterAnimation(characterAnimation);

        return camera;
    }

    private void centerCameraOnCharacterOnRandomPosition(
        final Camera camera,
        final Map newMap
    ) {
        int mapWidthPixels = newMap.getWidth() * 16;
        int mapHeightPixels = newMap.getHeight() * 16;
        int randomCharacterPositionX = random.nextInt(mapWidthPixels);
        int randomCharacterPositionY = random.nextInt(mapHeightPixels);
        characterAnimation.setScreenPosition(
            randomCharacterPositionX,
            randomCharacterPositionY
        );
        camera.centerCamera();
    }

    private void checkCenterCameraOnMiddleOnce(
        final Camera camera,
        final Map newMap
    ) {
        int mapWidthPixels = newMap.getWidth() * 16;
        int mapHeightPixels = newMap.getHeight() * 16;
        int middleScreenWidth = camera.getScreenWidth() / 2;
        int middleScreenHeight = camera.getScreenHeight() / 2;

        int randomCharacterPositionX = (
            random.nextInt(mapWidthPixels - camera.getScreenWidth())
            + middleScreenWidth
            - 8
        );
        int randomCharacterPositionY = (
            random.nextInt(mapHeightPixels - camera.getScreenHeight())
            + middleScreenHeight
        );

        characterAnimation.setScreenPosition(
            randomCharacterPositionX,
            randomCharacterPositionY
        );
        camera.centerCamera();

        int characterScreenPositionX = (
            randomCharacterPositionX - camera.getX() + 8
        );
        int characterScreenPositionY = randomCharacterPositionY - camera.getY();

        Assert.assertEquals(characterScreenPositionX, middleScreenWidth);
        Assert.assertEquals(characterScreenPositionY, middleScreenHeight);
    }
}

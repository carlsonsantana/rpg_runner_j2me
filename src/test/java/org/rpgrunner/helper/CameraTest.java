package org.rpgrunner.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.MapSpy;

public class CameraTest extends TestCase {
    private Random random;
    private CharacterAnimationSpy characterAnimation;
    private MapSpy map;

    public void setUp() {
        random = new Random();
        map = new MapSpy();

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

    public void testCenterCameraHorizontalMiddle() {
        int randomWidth = 320;
        int randomHeight = 320;
        map.setWidth(randomWidth/16);
        map.setHeight(randomHeight/16);

        Camera camera = new Camera(160, 160);
        characterAnimation.setScreenPosition(160, 0);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();

        Assert.assertEquals(88, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testCenterCameraHorizontalCorners() {
        int randomWidth = 320;
        int randomHeight = 320;
        map.setWidth(randomWidth/16);
        map.setHeight(randomHeight/16);

        Camera camera = new Camera(160, 160);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());

        characterAnimation.setScreenPosition(304, 0);

        camera.centerCamera();
        Assert.assertEquals(160, camera.getX());
        Assert.assertEquals(0, camera.getY());

        characterAnimation.setScreenPosition(0, 0);

        camera.centerCamera();
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }

    public void testCenterCameraVerticalMiddle() {
        int randomWidth = 320;
        int randomHeight = 320;
        map.setWidth(randomWidth/16);
        map.setHeight(randomHeight/16);

        Camera camera = new Camera(160, 160);
        characterAnimation.setScreenPosition(0, 160);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();

        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(80, camera.getY());
    }

    public void testCenterCameraVerticalCorners() {
        int randomWidth = 320;
        int randomHeight = 320;
        map.setWidth(randomWidth/16);
        map.setHeight(randomHeight/16);

        Camera camera = new Camera(160, 160);
        camera.setMap(map);
        camera.setCharacterAnimation(characterAnimation);
        camera.centerCamera();
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());

        characterAnimation.setScreenPosition(0, 304);

        camera.centerCamera();
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(160, camera.getY());

        characterAnimation.setScreenPosition(0, 0);

        camera.centerCamera();
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }
}

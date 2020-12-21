package org.rpgrunner.game.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.rpgrunner.game.character.CharacterElement;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.test.mock.CharacterSpy;
import org.rpgrunner.test.mock.CollisionDetectorSpy;
import org.rpgrunner.test.mock.MapSpy;

public class CameraTest extends TestCase {
    private Random random;
    private CharacterSpy character;
    private CharacterAnimationSpy characterAnimation;
    private CharacterElement characterElement;
    private MapSpy map;

    public void setUp() {
        random = new Random();
        map = new MapSpy();

        character = new CharacterSpy(null);
        characterAnimation = new CharacterAnimationSpy();
        CollisionDetectorSpy collisionDetector = new CollisionDetectorSpy();
        characterElement = new CharacterElement(
            collisionDetector,
            character,
            characterAnimation
        );
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

    public void testCenterCameraVertical() {
        int randomWidth = 320;
        int randomHeight = 320;
        map.setWidth(randomWidth/16);
        map.setHeight(randomHeight/16);

        Camera camera = new Camera(160, 160);
        camera.centerCamera(map, characterElement);
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());

        character.setInitialPosition(0, 19);
        characterAnimation.setScreenPosition(0, 304);

        camera.centerCamera(map, characterElement);
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(224, camera.getY());

        character.setInitialPosition(0, 0);
        characterAnimation.setScreenPosition(0, 0);

        camera.centerCamera(map, characterElement);
        Assert.assertEquals(0, camera.getX());
        Assert.assertEquals(0, camera.getY());
    }
}

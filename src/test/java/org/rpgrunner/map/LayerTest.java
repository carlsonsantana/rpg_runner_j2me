package org.rpgrunner.map;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.Direction;
import org.rpgrunner.test.helper.RandomGenerator;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.tileset.TileSetSpy;

public class LayerTest extends TestCase {
    private static final int TEST_REPEAT_LOOP = 100;
    private static final int TILE_SET_RESULTS_SIZE = 2 * TEST_REPEAT_LOOP;
    private static final int MINIMUM_LAYER_WIDTH = 2;
    private static final int MINIMUM_LAYER_HEIGHT = 2;
    private static final int MAXIMUM_LAYER_WIDTH = 100;
    private static final int MAXIMUM_LAYER_HEIGHT = 100;
    private Random random;
    private int width;
    private int height;
    private byte[] tileMap;
    private boolean[] tileSetResults;
    private TileSetSpy tileSetSpy;
    private Layer layer;

    public LayerTest() {
        random = new Random();
    }

    public void setUp() {
        tileSetResults = generateTileSetResults();
        tileSetSpy = new TileSetSpy(tileSetResults);
        width = random.nextInt(MAXIMUM_LAYER_WIDTH) + MINIMUM_LAYER_WIDTH;
        height = (
            random.nextInt(MAXIMUM_LAYER_HEIGHT)
            + MINIMUM_LAYER_HEIGHT
        );
        tileMap = generateRandomTileMap();
        layer = new Layer(tileSetSpy, tileMap, width, height);
    }

    private boolean[] generateTileSetResults() {
        boolean[] newTileSetResults = new boolean[TILE_SET_RESULTS_SIZE];

        for (int i = 0; i < TILE_SET_RESULTS_SIZE; i++) {
            newTileSetResults[i] = true;
        }

        return newTileSetResults;
    }

    private byte[] generateRandomTileMap() {
        byte[] newTileMap = new byte[height * width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newTileMap[(y * width) + x] = RandomGenerator.getRandomByte();
            }
        }

        return newTileMap;
    }

    public void testReturnSameTileMap() {
        Assert.assertSame(tileMap, layer.getTileMap());
    }

    public void testReturnSameWidthOfTileMap() {
        Assert.assertEquals(width, layer.getWidth());
    }

    public void testReturnSameHeightOfTileMap() {
        Assert.assertEquals(height, layer.getHeight());
    }

    public void testCanMoveToValidPositionsWithoutCollisionsLoop() {
        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCanMoveToValidPositionsWithoutCollisions();
        }
    }

    private void checkCanMoveToValidPositionsWithoutCollisions() {
        checkMoveToValidPositionsWithoutCollisions(true);
    }

    public void testCantMoveToNegativePositions() {
        CharacterSpy characterLeft = new CharacterSpy(null);
        characterLeft.setInitialPosition(0, 0);
        characterLeft.setAdditionalNextPosition(-1, 0);
        characterLeft.setDirection(Direction.LEFT);
        Assert.assertFalse(layer.canMove(characterLeft));

        CharacterSpy characterUp = new CharacterSpy(null);
        characterUp.setInitialPosition(0, 0);
        characterUp.setAdditionalNextPosition(0, -1);
        characterUp.setDirection(Direction.UP);
        Assert.assertFalse(layer.canMove(characterUp));
    }

    public void testCantMoveToPositionOffTheMap() {
        int borderX = layer.getWidth() - 1;
        int borderY = layer.getHeight() - 1;

        CharacterSpy characterRight = new CharacterSpy(null);
        characterRight.setInitialPosition(borderX, borderY);
        characterRight.setAdditionalNextPosition(1, 0);
        characterRight.setDirection(Direction.RIGHT);
        Assert.assertFalse(layer.canMove(characterRight));

        CharacterSpy characterDown = new CharacterSpy(null);
        characterDown.setInitialPosition(borderX, borderY);
        characterDown.setAdditionalNextPosition(0, 1);
        characterDown.setDirection(Direction.DOWN);
        Assert.assertFalse(layer.canMove(characterDown));
    }

    public void testCantMoveToPositionWhenHasCollisionLoop() {
        forceTileSetCollision();

        for (int i = 0; i < TEST_REPEAT_LOOP; i++) {
            checkCantMoveToValidPositionsWithoutCollisions();
        }
    }

    private void forceTileSetCollision() {
        for (int i = 0; i < TILE_SET_RESULTS_SIZE; i++) {
            tileSetResults[i] = false;
        }
    }

    private void checkCantMoveToValidPositionsWithoutCollisions() {
        checkMoveToValidPositionsWithoutCollisions(false);
    }

    private void checkMoveToValidPositionsWithoutCollisions(
        final boolean expectedValue
    ) {
        CharacterSpy character = new CharacterSpy(null);
        int maxX = layer.getWidth();
        int maxY = layer.getHeight();
        int randomValidX = random.nextInt(maxX);
        int randomValidY = random.nextInt(maxY);
        byte direction;
        boolean invalidPosition;

        character.setInitialPosition(randomValidX, randomValidY);

        do {
            direction = RandomGenerator.getRandomDirection();

            if (direction == Direction.UP) {
                character.setAdditionalNextPosition(0, -1);
            } else if (direction == Direction.RIGHT) {
                character.setAdditionalNextPosition(1, 0);
            } else if (direction == Direction.DOWN) {
                character.setAdditionalNextPosition(0, 1);
            } else {
                character.setAdditionalNextPosition(-1, 0);
            }

            int targetX = character.getMapNextPositionX();
            int targetY = character.getMapNextPositionY();

            invalidPosition = (
                (targetX < 0)
                || (targetX >= maxX)
                || (targetY < 0)
                || (targetY >= maxY)
            );
        } while (invalidPosition);

        Assert.assertEquals(expectedValue, layer.canMove(character));
    }
}

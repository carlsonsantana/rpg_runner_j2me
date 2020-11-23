package org.rpgrunner.game.helper;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.mock.MapSpy;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.test.helper.RandomGenerator;

public class CollisionDetectorTest extends TestCase {
    private CollisionDetector collisionDetector;

    public void setUp() {
        collisionDetector = new CollisionDetector();
    }

    public void testReturnSameMap() {
        MapSpy map = new MapSpy();
        collisionDetector.setMap(map);

        Assert.assertEquals(map, collisionDetector.getMap());
    }

    public void testReturnSameEmptyCharacters() {
        GameCharacter[] characters = new GameCharacter[0];
        collisionDetector.setCharacters(characters);
        GameCharacter[] sameCharacters = collisionDetector.getCharacters();

        Assert.assertEquals(characters, sameCharacters);
        Assert.assertEquals(0, sameCharacters.length);
    }

    public void testReturnSameCharacters() {
        Random random = new Random();
        int numberCharacters = random.nextInt(100);
        GameCharacter[] characters = new GameCharacter[numberCharacters];
        GameCharacter[] copyCharacters = new GameCharacter[numberCharacters];

        for (int i = 0; i < numberCharacters; i++) {
            GameCharacter character = generateRandomCharacter();
            characters[i] = character;
            copyCharacters[i] = character;
        }

        collisionDetector.setCharacters(characters);
        GameCharacter[] sameCharacters = collisionDetector.getCharacters();

        Assert.assertEquals(characters, sameCharacters);
        Assert.assertEquals(numberCharacters, sameCharacters.length);
        for (int i = 0; i < numberCharacters; i++) {
            Assert.assertEquals(sameCharacters[i], copyCharacters[i]);
        }
    }

    private GameCharacter generateRandomCharacter() {
        String randomFileBaseName = RandomGenerator.getRandomString();
        return new GameCharacter(randomFileBaseName);
    }
}

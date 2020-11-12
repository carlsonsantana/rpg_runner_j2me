package org.rpgrunner.game.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;

public class GameCharacterTest extends TestCase {
    public void testReturnSameFileBaseName() {
        String randomFileBaseName = RandomGenerator.getRandomString();
        GameCharacter gameCharacter = new GameCharacter(randomFileBaseName);
        Assert.assertEquals(
            randomFileBaseName,
            gameCharacter.getFileBaseName()
        );
    }
}

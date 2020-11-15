package org.rpgrunner.game.tileset;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;

public class TileSetTest extends TestCase {
    public void testReturnSameName() {
        String randomName = RandomGenerator.getRandomString();
        TileSet tileSet = new TileSet(randomName, null);
        Assert.assertEquals(randomName, tileSet.getName());
    }
}

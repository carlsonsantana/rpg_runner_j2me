package org.rpgrunner.test.mock;

import org.rpgrunner.game.tileset.TileSet;

public class TileSetSpy extends TileSet {
    private int resultsIndex;
    private boolean[] results;

    public TileSetSpy(final boolean[] spyResults) {
        super(null, null);
        resultsIndex = 0;
        results = spyResults;
    }

    public String getName() {
        return null;
    }

    public boolean canPassOn(final int tileIndex, final byte direction) {
        return results[resultsIndex++];
    }
}

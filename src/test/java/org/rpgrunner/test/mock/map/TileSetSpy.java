package org.rpgrunner.test.mock.map;

import org.rpgrunner.map.TileSet;

public class TileSetSpy extends TileSet {
    private int resultsIndex;
    private boolean[] results;

    public TileSetSpy(final boolean[] spyResults) {
        super((byte) 0, null);
        resultsIndex = 0;
        results = spyResults;
    }

    public boolean canPassOn(final int tileIndex, final byte direction) {
        return results[resultsIndex++];
    }
}

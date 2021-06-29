package org.rpgrunner.map;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.tileset.TileSet;

public class Layer {
    private final TileSet tileSet;
    private final byte[][] tileMap;

    public Layer(final TileSet mapTileSet, final byte[][] mapTileMap) {
        tileSet = mapTileSet;
        tileMap = mapTileMap;
    }

    public int getWidth() {
        return tileMap[0].length;
    }

    public int getHeight() {
        return tileMap.length;
    }

    public byte[][] getTileMap() {
        return tileMap;
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    public boolean canMove(final GameCharacter character) {
        int fromX = character.getMapPositionX();
        int fromY = character.getMapPositionY();
        int toX = character.getMapNextPositionX();
        int toY = character.getMapNextPositionY();
        byte direction = character.getDirection();

        return (
            isValidPosition(toX, toY)
            && (tileSet.canPassOn(tileMap[fromY][fromX], direction))
            && (
                tileSet.canPassOn(
                    tileMap[toY][toX],
                    Direction.invertDirection(direction)
                )
            )
        );
    }

    private boolean isValidPosition(final int x, final int y) {
        return ((x >= 0) && (y >= 0) && (x < getWidth()) && (y < getHeight()));
    }
}

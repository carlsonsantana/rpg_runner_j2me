package org.rpgrunner.map;

import org.rpgrunner.Direction;
import org.rpgrunner.character.GameCharacter;

public class Layer {
    private final TileSet tileSet;
    private final byte[] tileMap;
    private final int width;
    private final int height;

    public Layer(
        final TileSet mapTileSet,
        final byte[] mapTileMap,
        final int mapWidth,
        final int mapHeight
    ) {
        tileSet = mapTileSet;
        tileMap = mapTileMap;
        width = mapWidth;
        height = mapHeight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[] getTileMap() {
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
            && (tileSet.canPassOn(tileMap[(fromY * width) + fromX], direction))
            && (
                tileSet.canPassOn(
                    tileMap[(toY * width) + toX],
                    Direction.invertDirection(direction)
                )
            )
        );
    }

    private boolean isValidPosition(final int x, final int y) {
        return ((x >= 0) && (y >= 0) && (x < getWidth()) && (y < getHeight()));
    }
}

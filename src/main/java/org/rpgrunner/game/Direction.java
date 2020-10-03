package org.rpgrunner.game;

public final class Direction {
    public static final byte UP = 8;
    public static final byte RIGHT = 4;
    public static final byte DOWN = 2;
    public static final byte LEFT = 1;

    private Direction() { }

    public static boolean isUp(final byte direction) {
        return direction == UP;
    }

    public static boolean isRight(final byte direction) {
        return direction == RIGHT;
    }

    public static boolean isDown(final byte direction) {
        return direction == DOWN;
    }

    public static boolean isLeft(final byte direction) {
        return direction == LEFT;
    }
}

package org.rpgrunner.test.mock.event;

import org.rpgrunner.Direction;
import org.rpgrunner.event.MapEventArea;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public class MapEventAreaSpy extends MapEventArea {
    private int x;
    private int y;
    private int width;
    private int height;
    private Action interactAction;

    public MapEventAreaSpy() {
        super(0, 0, 0, 0, Direction.NO_DIRECTION, null);
        interactAction = new NullAction();
    }

    public void setX(final int newX) {
        x = newX;
    }

    public int getX() {
        return x;
    }

    public void setY(final int newY) {
        y = newY;
    }

    public int getY() {
        return y;
    }

    public void setWidth(final int newWidth) {
        width = newWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(final int newHeight) {
        height = newHeight;
    }

    public int getHeight() {
        return height;
    }

    public void setInteractAction(final Action newInteractAction) {
        interactAction = newInteractAction;
    }

    public Action interact(final byte direction) {
        return interactAction;
    }
}

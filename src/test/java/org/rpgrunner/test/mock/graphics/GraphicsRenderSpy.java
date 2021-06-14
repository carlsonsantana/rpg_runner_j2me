package org.rpgrunner.test.mock.graphics;

import org.rpgrunner.graphics.GraphicsRender;

public abstract class GraphicsRenderSpy implements GraphicsRender {
    private boolean renderCalled;

    public void render() {
        renderCalled = true;
    }

    public boolean isRenderCalled() {
        return renderCalled;
    }
}

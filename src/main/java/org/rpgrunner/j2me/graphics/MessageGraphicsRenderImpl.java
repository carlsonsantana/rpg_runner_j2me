package org.rpgrunner.j2me.graphics;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.graphics.MessageGraphicsRender;
import org.rpgrunner.helper.Camera;

public class MessageGraphicsRenderImpl implements MessageGraphicsRender {
    private static final int BORDER_COLOR = 0xFF0000;
    private static final int BACKGROUND_COLOR = 0x000000;
    private static final int TEXT_COLOR = 0xFFFFFF;
    private static final int BOX_PROPORTION = 4;
    private static final int TEXT_PADDING = 7;

    private final Graphics graphics;
    private final LayerManager layerManager;
    private final int boxWidth;
    private final int boxHeight;
    private final int screenHeight;
    private final int boxPositionY;
    private final int fontHeight;
    private final int textBoxWidth;

    private String currentMessage;
    private int[] cacheLinesLengths;
    private int scrollY;

    public MessageGraphicsRenderImpl(
        final Graphics midletGraphics,
        final Camera camera
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();

        boxWidth = camera.getScreenWidth();
        screenHeight = camera.getScreenHeight();
        boxHeight = screenHeight / BOX_PROPORTION;
        boxPositionY = screenHeight - boxHeight;
        fontHeight = graphics.getFont().getHeight();
        textBoxWidth = boxWidth - (TEXT_PADDING * 2);
    }

    public void showMessage(final String message) {
        currentMessage = message;
    }

    public void hideMessage() {
        currentMessage = null;
        cacheLinesLengths = null;
    }

    public void render() {
        showCurrentMessage();
    }

    private void showCurrentMessage() {
        if (currentMessage == null) {
            return;
        }

        displayMessage();
    }

    private void displayMessage() {
        graphics.setClip(0, boxPositionY, boxWidth, boxHeight);
        drawBox();
        drawText();
        graphics.setClip(0, 0, boxWidth, screenHeight);
    }

    private void drawBox() {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, boxPositionY, boxWidth, boxHeight);
        graphics.setColor(BORDER_COLOR);
        graphics.drawRect(0, boxPositionY, boxWidth - 1, boxHeight - 1);
    }

    private void drawText() {
        graphics.setColor(TEXT_COLOR);

        int[] linesLengths = getLinesLengthsProxy();
        int currentOffset = 0;

        for (
            int i = 0, arrayLength = linesLengths.length;
            i < arrayLength;
            i++
        ) {
            int lineLength = linesLengths[i];
            int positionY = (
                boxPositionY + i * fontHeight + TEXT_PADDING - scrollY
            );

            graphics.drawSubstring(
                currentMessage,
                currentOffset,
                lineLength,
                TEXT_PADDING,
                positionY,
                Graphics.TOP | Graphics.LEFT
            );

            currentOffset += lineLength;
        }
    }

    public void scrollUp() {
        if (scrollY <= 0) {
            return;
        }

        scrollY--;
    }

    public void scrollDown() {
        if (isMaxScrollDown()) {
            return;
        }

        scrollY++;
    }

    private boolean isMaxScrollDown() {
        int numberOfLines = getLinesLengthsProxy().length;
        int textHeight = numberOfLines * fontHeight;

        return ((textHeight + TEXT_PADDING) <= (boxHeight + scrollY));
    }

    private int[] getLinesLengthsProxy() {
        if (cacheLinesLengths == null) {
            Vector lines = getLinesLengths();
            cacheLinesLengths = new int[lines.size()];
            int i = 0;

            for (
                Enumeration enumeration = lines.elements();
                enumeration.hasMoreElements();
                i++
            ) {
                int length = ((Integer) enumeration.nextElement()).intValue();
                cacheLinesLengths[i] = length;
            }
        }

        return cacheLinesLengths;
    }

    private Vector getLinesLengths() {
        Vector lines = new Vector();
        int messageLength = currentMessage.length();
        int lineIndex = 0;
        int lineLength = 0;

        for (
            int searchIndex = 0,
            spaceIndex = currentMessage.indexOf(" ", searchIndex);
            ((searchIndex < messageLength) && (spaceIndex) >= 0);
            searchIndex = lineIndex + lineLength,
            spaceIndex = currentMessage.indexOf(" ", searchIndex)
        ) {
            int substringLength = spaceIndex - lineIndex + 1;

            int lineWidth = graphics.getFont().substringWidth(
                currentMessage,
                lineIndex,
                substringLength
            );

            if (lineWidth < textBoxWidth) {
                lineLength = substringLength;
            } else {
                lines.addElement(new Integer(lineLength));
                lineIndex += lineLength;
                lineLength = substringLength - lineLength;
            }
        }

        lines.addElement(new Integer(messageLength - lineIndex));

        return lines;
    }
}

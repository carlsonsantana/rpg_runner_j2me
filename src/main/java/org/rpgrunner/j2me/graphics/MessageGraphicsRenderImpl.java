package org.rpgrunner.j2me.graphics;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageGraphicsRenderImpl implements MessageGraphicsRender {
    private static final int BORDER_COLOR = 0xFF0000;
    private static final int BACKGROUND_COLOR = 0x000000;
    private static final int TEXT_COLOR = 0xFFFFFF;
    private static final int BOX_PROPORTION = 4;
    private static final int TEXT_PADDING = 7;
    private static final int OUTLINE_OFFSET = 1;
    private static final int DISPLAY_LINES = 4;

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
        final int currentScreenWidth,
        final int currentScreenHeight
    ) {
        graphics = midletGraphics;
        layerManager = new LayerManager();

        screenHeight = currentScreenHeight;
        fontHeight = graphics.getFont().getHeight();
        boxWidth = currentScreenWidth;
        boxHeight = (fontHeight * DISPLAY_LINES) + (TEXT_PADDING * 2);
        boxPositionY = screenHeight - boxHeight;
        textBoxWidth = boxWidth - (TEXT_PADDING * 2);
    }

    public void showMessage(final String message) {
        currentMessage = message;

        cacheLinesLengths = null;
        scrollY = 0;

        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, boxPositionY, boxWidth, boxHeight);
        graphics.setColor(BORDER_COLOR);
        graphics.drawRect(
            OUTLINE_OFFSET,
            boxPositionY + OUTLINE_OFFSET,
            boxWidth - OUTLINE_OFFSET - 2,
            boxHeight - OUTLINE_OFFSET - 2
        );
        graphics.setClip(
            TEXT_PADDING,
            boxPositionY + TEXT_PADDING,
            textBoxWidth,
            boxHeight - (2 * TEXT_PADDING)
        );
    }

    public void hideMessage() {
        currentMessage = null;
        graphics.setClip(0, 0, boxWidth, screenHeight);
    }

    public void render() {
        if (currentMessage == null) {
            return;
        }

        displayMessage();
    }

    private void displayMessage() {
        clearBox();
        drawText();
    }

    private void clearBox() {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, boxPositionY, boxWidth, boxHeight);
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

        return ((textHeight + (2 * TEXT_PADDING)) <= (boxHeight + scrollY));
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

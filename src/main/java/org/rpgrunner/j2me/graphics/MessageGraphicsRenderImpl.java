package org.rpgrunner.j2me.graphics;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;

import org.rpgrunner.graphics.MessageGraphicsRender;

public class MessageGraphicsRenderImpl implements MessageGraphicsRender {
    private static final int OUTLINE_COLOR = 0xFF0000;
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
    private int[] linesLengths;
    private int linesLengthsSize;
    private int currentMessageIndex;
    private int currentMessageLength;

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

        linesLengths = new int[DISPLAY_LINES];
    }

    public void showMessage(final String message) {
        currentMessage = message;
        currentMessageIndex = 0;
        currentMessageLength = 0;

        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, boxPositionY, boxWidth, boxHeight);
        graphics.setColor(OUTLINE_COLOR);
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

        updateLinesLengths();
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

        int currentOffset = currentMessageIndex;

        for (int i = 0; i <= linesLengthsSize; i++) {
            int lineLength = linesLengths[i];
            int positionY = boxPositionY + i * fontHeight + TEXT_PADDING;

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

    public void pageUp() { }

    public void pageDown() {
        if (isMaxScrollDown()) {
            return;
        }

        currentMessageIndex += currentMessageLength;
        updateLinesLengths();
    }

    private boolean isMaxScrollDown() {
        return (
            currentMessage.length()
            == currentMessageIndex + currentMessageLength
        );
    }

    private void updateLinesLengths() {
        currentMessageLength = 0;
        linesLengthsSize = 0;

        int messageLength = currentMessage.length();
        int lineIndex = currentMessageIndex;
        int lineLength = 0;

        for (
            int searchIndex = currentMessageIndex,
            spaceIndex = currentMessage.indexOf(" ", searchIndex);
            (
                (linesLengthsSize < DISPLAY_LINES)
                && (searchIndex < messageLength)
                && ((spaceIndex) >= 0)
            );
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
                linesLengths[linesLengthsSize++] = lineLength;
                currentMessageLength += lineLength;
                lineIndex += lineLength;
                lineLength = substringLength - lineLength;
            }
        }

        if (linesLengthsSize < DISPLAY_LINES) {
            linesLengths[linesLengthsSize] = messageLength - lineIndex;
            currentMessageLength += messageLength - lineIndex;
        } else {
            linesLengthsSize--;
        }
    }
}

package org.rpgrunner.j2me.graphics;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import org.rpgrunner.helper.Camera;

public class MessageDialog {
    private static final int BORDER_COLOR = 0xFF0000;
    private static final int BACKGROUND_COLOR = 0x000000;
    private static final int TEXT_COLOR = 0xFFFFFF;
    private static final int BOX_PROPORTION = 4;
    private static final int TEXT_PADDING = 7;

    private final String message;
    private final Graphics graphics;
    private final Camera camera;
    private int[] cacheLinesLengths;

    public MessageDialog(
        final String messageToShow,
        final Graphics midletGraphics,
        final Camera currentCamera
    ) {
        message = messageToShow;
        graphics = midletGraphics;
        camera = currentCamera;
    }

    public void display() {
        int boxWidth = camera.getScreenWidth();
        int screenHeight = camera.getScreenHeight();
        int boxHeight = screenHeight / BOX_PROPORTION;
        int boxPositionY = screenHeight - boxHeight;

        drawBox(boxPositionY, boxWidth, boxHeight);
        drawText(boxPositionY, boxWidth, boxHeight);
    }

    private void drawBox(
        final int boxPositionY,
        final int boxWidth,
        final int boxHeight
    ) {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, boxPositionY, boxWidth, boxHeight);
        graphics.setColor(BORDER_COLOR);
        graphics.drawRect(0, boxPositionY, boxWidth - 1, boxHeight - 1);
    }

    private void drawText(
        final int boxPositionY,
        final int boxWidth,
        final int boxHeight
    ) {
        graphics.setColor(TEXT_COLOR);

        int textBoxWidth = boxWidth - (TEXT_PADDING * 2);
        int[] linesLengths = getLinesLengthsProxy(textBoxWidth);
        int fontHeight = graphics.getFont().getHeight();
        int currentOffset = 0;

        for (
            int i = 0, arrayLength = linesLengths.length;
            i < arrayLength;
            i++
        ) {
            int lineLength = linesLengths[i];
            int positionY = boxPositionY + i * fontHeight + TEXT_PADDING;

            graphics.drawSubstring(
                message,
                currentOffset,
                lineLength,
                TEXT_PADDING,
                positionY,
                Graphics.TOP | Graphics.LEFT
            );

            currentOffset += lineLength;
        }
    }

    private int[] getLinesLengthsProxy(final int textBoxWidth) {
        if (cacheLinesLengths == null) {
            Vector lines = getLinesLengths(textBoxWidth);
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

    private Vector getLinesLengths(final int textBoxWidth) {
        Vector lines = new Vector();
        int messageLength = message.length();
        int lineIndex = 0;
        int lineLength = 0;

        for (
            int searchIndex = 0, spaceIndex = message.indexOf(" ", searchIndex);
            ((searchIndex < messageLength) && (spaceIndex) >= 0);
            searchIndex = lineIndex + lineLength,
            spaceIndex = message.indexOf(" ", searchIndex)
        ) {
            int substringLength = spaceIndex - lineIndex + 1;

            int lineWidth = graphics.getFont().substringWidth(
                message,
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

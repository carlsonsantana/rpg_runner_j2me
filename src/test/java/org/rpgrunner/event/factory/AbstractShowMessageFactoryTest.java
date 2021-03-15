package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.GameController;
import org.rpgrunner.event.action.AbstractShowMessageTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.ShowMessage;
import org.rpgrunner.test.helper.InputStreamHelper;

public abstract class AbstractShowMessageFactoryTest
    extends AbstractShowMessageTest {
    private static final int ADDITIONAL_BYTES = 1;

    protected ShowMessage createShowMessage(
        final GameController gameController,
        final String message
    ) {
        InputStream inputStream = getInputStream(message);
        try {
            ShowMessage showMessage = (
                (ShowMessage) createAction(inputStream, gameController)
            );

            return showMessage;
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream getInputStream(final String message) {
        int arraySize = InputStreamHelper.getStringLength(message);
        byte[] byteArray = new byte[arraySize + ADDITIONAL_BYTES];
        InputStreamHelper.setByteArray(byteArray, message);

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        InputStream inputStream,
        GameController gameController
    ) throws IOException;
}

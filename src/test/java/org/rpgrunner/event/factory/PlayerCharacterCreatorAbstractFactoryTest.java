package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.HelperActionAbstractFactory;

public class PlayerCharacterCreatorAbstractFactoryTest
    extends AbstractPlayerCharacterCreatorFactoryTest {
    private static final byte PLAYER_CHARACTER_CREATOR_FACTORY = (byte) 2;

    protected InputStream generateInputStream(final byte[] byteArray) {
        int length = byteArray.length;
        int newLength = length + 1;
        byte[] newByteArray = new byte[newLength];

        newByteArray[0] = PLAYER_CHARACTER_CREATOR_FACTORY;
        System.arraycopy(byteArray, 0, newByteArray, 1, length);

        return new ByteArrayInputStream(newByteArray);
    }

    protected Action createAction(
        final MapController mapController,
        final InputStream inputStream
    ) throws IOException {
        return HelperActionAbstractFactory.createAction(
            mapController,
            inputStream
        );
    }
}

package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.test.helper.HelperActionAbstractFactory;

public class NullActionAbstractFactoryTest
    extends AbstractNullActionFactoryTest {
    private static final byte NULL_ACTION_FACTORY = (byte) 0;

    protected InputStream generateInputStream(final byte[] byteArray) {
        int length = byteArray.length;
        int newLength = length + 1;
        byte[] newByteArray = new byte[newLength];

        newByteArray[0] = NULL_ACTION_FACTORY;
        System.arraycopy(byteArray, 0, newByteArray, 1, length);

        return new ByteArrayInputStream(newByteArray);
    }

    protected Action createAction(
        final InputStream inputStream
    ) throws IOException {
        return HelperActionAbstractFactory.createAction(inputStream);
    }
}

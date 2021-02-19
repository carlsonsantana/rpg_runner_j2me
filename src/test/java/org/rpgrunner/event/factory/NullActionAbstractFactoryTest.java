package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;

public class NullActionAbstractFactoryTest
    extends AbstractNullActionFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        int length = byteArray.length;
        int newLength = length + 1;
        byte[] newByteArray = new byte[newLength];

        newByteArray[0] = (byte) 0;
        System.arraycopy(byteArray, 0, newByteArray, 1, length);

        return new ByteArrayInputStream(newByteArray);
    }

    protected Action createAction(final InputStream inputStream) {
        NullActionFactory nullActionFactory = new NullActionFactory();
        Action action = nullActionFactory.create(inputStream);

        return action;
    }
}

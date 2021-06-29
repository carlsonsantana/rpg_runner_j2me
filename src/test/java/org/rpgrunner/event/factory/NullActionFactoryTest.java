package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;

public class NullActionFactoryTest extends AbstractNullActionFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(final InputStream inputStream) {
        NullActionFactory nullActionFactory = new NullActionFactory();
        Action action = nullActionFactory.create(inputStream);

        return action;
    }
}

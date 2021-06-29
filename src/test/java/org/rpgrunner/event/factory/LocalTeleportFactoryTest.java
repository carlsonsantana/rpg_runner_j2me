package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.event.action.Action;

public class LocalTeleportFactoryTest extends AbstractLocalTeleportFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(
        final InputStream inputStream
    ) throws IOException {
        LocalTeleportFactory localTeleportFactory = new LocalTeleportFactory();
        Action action = localTeleportFactory.create(inputStream);

        return action;
    }
}

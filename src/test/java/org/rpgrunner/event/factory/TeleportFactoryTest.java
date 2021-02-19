package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.map.MapLoader;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class TeleportFactoryTest extends AbstractTeleportFactoryTest {
    private final MapLoader mapLoader;

    public TeleportFactoryTest() {
        mapLoader = new MapLoader(new ActionAbstractFactorySpy());
    }

    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(
        final InputStream inputStream,
        final GameController gameController
    ) throws IOException {
        TeleportFactory teleportFactory = new TeleportFactory(
            gameController,
            mapLoader
        );
        Action action = teleportFactory.create(inputStream);

        return action;
    }
}

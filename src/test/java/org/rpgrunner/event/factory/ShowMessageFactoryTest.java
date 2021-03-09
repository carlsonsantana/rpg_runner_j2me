package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.event.action.Action;

public class ShowMessageFactoryTest extends AbstractShowMessageFactoryTest {
    protected InputStream generateInputStream(final byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }

    protected Action createAction(
        final InputStream inputStream,
        final GameController gameController
    ) throws IOException {
        ShowMessageFactory showMessageFactory = new ShowMessageFactory(
            gameController
        );
        Action action = showMessageFactory.create(inputStream);

        return action;
    }
}

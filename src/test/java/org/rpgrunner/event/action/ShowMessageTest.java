package org.rpgrunner.event.action;

import org.rpgrunner.GameController;

public class ShowMessageTest extends AbstractShowMessageTest {
    protected ShowMessage createShowMessage(
        final GameController gameController,
        final String message
    ) {
        return new ShowMessage(gameController, message);
    }
}

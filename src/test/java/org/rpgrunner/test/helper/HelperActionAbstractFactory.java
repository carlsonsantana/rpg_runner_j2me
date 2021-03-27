package org.rpgrunner.test.helper;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;
import org.rpgrunner.test.mock.controller.GameControllerSpy;

public final class HelperActionAbstractFactory {
    private HelperActionAbstractFactory() { }

    public static ActionAbstractFactory createActionAbstractFactory() {
        GameControllerSpy gameController = new GameControllerSpy();

        return createActionAbstractFactory(gameController);
    }

    public static ActionAbstractFactory createActionAbstractFactory(
        final GameController gameController
    ) {
        CharacterAnimationFactory characterAnimationFactory = (
            new CharacterAnimationFactoryMock()
        );
        PlayerMovementFactory playerMovementFactory = (
            new PlayerMovementFactoryMock()
        );

        return new ActionAbstractFactory(
            gameController,
            characterAnimationFactory,
            playerMovementFactory
        );
    }

    public static Action createAction(
        final InputStream inputStream
    ) throws IOException {
        GameControllerSpy gameController = new GameControllerSpy();

        return createAction(gameController, inputStream);
    }

    public static Action createAction(
        final GameController gameController,
        final InputStream inputStream
    ) throws IOException {
        ActionAbstractFactory actionAbstractFactory =
            createActionAbstractFactory(gameController);

        return actionAbstractFactory.create(inputStream);
    }
}

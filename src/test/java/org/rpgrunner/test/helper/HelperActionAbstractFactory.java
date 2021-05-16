package org.rpgrunner.test.helper;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.factory.ActionAbstractFactory;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;
import org.rpgrunner.test.mock.controller.GameControllerSpy;
import org.rpgrunner.test.mock.controller.MapControllerSpy;

public final class HelperActionAbstractFactory {
    private HelperActionAbstractFactory() { }

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
        MapController mapController = new MapControllerSpy();

        return createAction(gameController, mapController, null, inputStream);
    }

    public static Action createAction(
        final MapController mapController,
        final InputStream inputStream
    ) throws IOException {
        return createAction(null, mapController, null, inputStream);
    }

    public static Action createAction(
        final MapController mapController,
        final ActionQueue actionQueue,
        final InputStream inputStream
    ) throws IOException {
        return createAction(null, mapController, actionQueue, inputStream);
    }

    private static Action createAction(
        final GameController gameController,
        final MapController mapController,
        final ActionQueue actionQueue,
        final InputStream inputStream
    ) throws IOException {
        ActionAbstractFactory actionAbstractFactory = (
            createActionAbstractFactory(
                gameController,
                mapController,
                actionQueue
            )
        );

        return actionAbstractFactory.create(inputStream);
    }

    public static ActionAbstractFactory createActionAbstractFactory() {
        MapController mapController = new MapControllerSpy();

        return createActionAbstractFactory(mapController);
    }

    public static ActionAbstractFactory createActionAbstractFactory(
        final MapController mapController
    ) {
        return createActionAbstractFactory(null, mapController, null);
    }

    private static ActionAbstractFactory createActionAbstractFactory(
        final GameController gameController,
        final MapController mapController,
        final ActionQueue actionQueue
    ) {
        CharacterAnimationFactory characterAnimationFactory = (
            new CharacterAnimationFactoryMock()
        );
        PlayerMovementFactory playerMovementFactory = (
            new PlayerMovementFactoryMock()
        );

        return new ActionAbstractFactory(
            gameController,
            mapController,
            characterAnimationFactory,
            playerMovementFactory,
            actionQueue
        );
    }
}

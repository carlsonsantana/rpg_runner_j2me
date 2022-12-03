package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.CharacterEventListener;
import org.rpgrunner.event.MapEvent;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;

public class CharacterCreatorFactory implements IdentifiedActionFactory {
    private static final int ID_VALUE = 3;
    private final MapController mapController;
    private final CharacterFactory characterFactory;
    private final ActionAbstractFactory actionAbstractFactory;

    public CharacterCreatorFactory(
        final MapController currentMapController,
        final CharacterFactory currentCharacterFactory,
        final ActionAbstractFactory currentActionAbstractFactory
    ) {
        mapController = currentMapController;
        characterFactory = currentCharacterFactory;
        actionAbstractFactory = currentActionAbstractFactory;
    }

    public int getId() {
        return ID_VALUE;
    }

    public Action create(final InputStream inputStream) throws IOException {
        byte idSprite = (byte) inputStream.read();
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();
        CharacterEventListener characterEventListener = (
            createCharacterEventListener(inputStream)
        );

        return new CharacterCreator(
            mapController,
            characterFactory,
            idSprite,
            mapPositionX,
            mapPositionY,
            characterEventListener
        );
    }

    private CharacterEventListener createCharacterEventListener(
        final InputStream inputStream
    ) throws IOException {
        int numberOfEvents = inputStream.read();
        MapEvent[] events = new MapEvent[numberOfEvents];

        for (int i = 0; i < numberOfEvents; i++) {
            byte directions = (byte) inputStream.read();
            Action action = actionAbstractFactory.create(inputStream);
            MapEvent mapEvent = new MapEvent(action, directions);
            events[i] = mapEvent;
        }

        return new CharacterEventListener(events);
    }
}

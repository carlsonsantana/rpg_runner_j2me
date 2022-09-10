package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.action.AbstractPlayerCharacterCreatorTest;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.PlayerCharacterCreator;
import org.rpgrunner.test.helper.InputStreamHelper;

public abstract class AbstractPlayerCharacterCreatorFactoryTest
extends AbstractPlayerCharacterCreatorTest {
    private static final int NUMBER_BYTES = 3;

    protected PlayerCharacterCreator createPlayerCharacterCreator(
        final MapController mapController,
        final byte characterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        InputStream inputStream = getInputStream(
            characterIDSprite,
            initialMapPositionX,
            initialMapPositionY
        );

        try {
            PlayerCharacterCreator playerCharacterCreator = (
                (PlayerCharacterCreator) createAction(
                    mapController,
                    inputStream
                )
            );

            return playerCharacterCreator;
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private InputStream getInputStream(
        final byte characterIDSprite,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        byte[] byteArray = new byte[NUMBER_BYTES];
        byteArray[0] = characterIDSprite;
        InputStreamHelper.setPosition(
            byteArray,
            1,
            initialMapPositionX,
            initialMapPositionY
        );

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(
        MapController mapController,
        InputStream inputStream
    ) throws IOException;
}

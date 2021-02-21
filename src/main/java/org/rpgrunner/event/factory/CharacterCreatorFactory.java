package org.rpgrunner.event.factory;

import java.io.IOException;
import java.io.InputStream;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.CharacterCreator;
import org.rpgrunner.helper.Loader;

public class CharacterCreatorFactory implements ActionFactory {
    private final GameController gameController;
    private final CharacterAnimationFactory characterAnimationFactory;
    private final ActionAbstractFactory actionAbstractFactory;

    public CharacterCreatorFactory(
        final GameController currentGameController,
        final CharacterAnimationFactory currentCharacterAnimationFactory,
        final ActionAbstractFactory currentActionAbstractFactory
    ) {
        gameController = currentGameController;
        characterAnimationFactory = currentCharacterAnimationFactory;
        actionAbstractFactory = currentActionAbstractFactory;
    }

    public Action create(final InputStream inputStream) throws IOException {
        String fileBaseName = Loader.extractString(inputStream);
        int mapPositionX = inputStream.read();
        int mapPositionY = inputStream.read();

        return create(
            fileBaseName,
            mapPositionX,
            mapPositionY,
            inputStream
        );
    }

    private AbstractCharacterCreator create(
        final String fileBaseName,
        final int mapPositionX,
        final int mapPositionY,
        final InputStream inputStream
    ) throws IOException {
        Action action = actionAbstractFactory.create(inputStream);

        CharacterCreator characterCreator = new CharacterCreator(
            gameController,
            characterAnimationFactory,
            fileBaseName,
            mapPositionX,
            mapPositionY,
            action
        );

        return characterCreator;
    }
}

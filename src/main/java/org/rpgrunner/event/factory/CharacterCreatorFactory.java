package org.rpgrunner.event.factory;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.event.action.AbstractCharacterCreator;
import org.rpgrunner.event.action.CharacterCreator;

public class CharacterCreatorFactory extends AbstractCharacterCreatorFactory {
    private final GameController gameController;
    private final CharacterAnimationFactory characterAnimationFactory;

    public CharacterCreatorFactory(
        final GameController currentGameController,
        final CharacterAnimationFactory currentCharacterAnimationFactory
    ) {
        gameController = currentGameController;
        characterAnimationFactory = currentCharacterAnimationFactory;
    }

    protected AbstractCharacterCreator create(
        final String fileBaseName,
        final int mapPositionX,
        final int mapPositionY
    ) {
        CharacterCreator characterCreator = new CharacterCreator(
            gameController,
            characterAnimationFactory,
            fileBaseName,
            mapPositionX,
            mapPositionY
        );

        return characterCreator;
    }
}

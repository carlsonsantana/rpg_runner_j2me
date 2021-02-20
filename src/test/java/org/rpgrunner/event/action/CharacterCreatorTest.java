package org.rpgrunner.event.action;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;

public class CharacterCreatorTest extends AbstractCharacterCreatorTest {
    private final CharacterAnimationFactory characterAnimationFactory;

    public CharacterCreatorTest() {
        characterAnimationFactory = new CharacterAnimationFactoryMock();
    }

    protected CharacterCreator createCharacterCreator(
        final GameController gameController,
        final String characterFileName,
        final int initialMapPositionX,
        final int initialMapPositionY
    ) {
        return new CharacterCreator(
            gameController,
            characterAnimationFactory,
            characterFileName,
            initialMapPositionX,
            initialMapPositionY
        );
    }
}

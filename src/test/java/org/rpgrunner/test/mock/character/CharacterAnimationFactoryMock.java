package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;

public class CharacterAnimationFactoryMock
implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final CharacterAnimation character
    ) {
        return new CharacterAnimationSpy();
    }
}

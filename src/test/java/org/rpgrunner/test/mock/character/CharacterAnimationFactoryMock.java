package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.mock.character.CharacterAnimationSpy;

public class CharacterAnimationFactoryMock
        implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final GameCharacter character
    ) {
        return new CharacterAnimationSpy();
    }
}

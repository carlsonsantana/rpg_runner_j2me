package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.test.mock.CharacterAnimationSpy;
import org.rpgrunner.character.GameCharacter;

public class CharacterAnimationFactoryMock
    implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final GameCharacter character
    ) {
        return new CharacterAnimationSpy();
    }
}

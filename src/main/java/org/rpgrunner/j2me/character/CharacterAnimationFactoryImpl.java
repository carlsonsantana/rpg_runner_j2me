package org.rpgrunner.j2me.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;

public class CharacterAnimationFactoryImpl
implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final CharacterAnimation character
    ) {
        return new CharacterAnimationImpl(character);
    }
}

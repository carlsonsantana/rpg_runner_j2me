package org.rpgrunner.j2me.character;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.j2me.CharacterAnimationImpl;

public class CharacterAnimationFactoryImpl
        implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final GameCharacter character
    ) {
        return new CharacterAnimationImpl(character);
    }
}

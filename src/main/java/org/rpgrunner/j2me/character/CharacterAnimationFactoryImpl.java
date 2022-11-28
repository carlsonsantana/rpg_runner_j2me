package org.rpgrunner.j2me.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.event.CharacterEventListener;

public class CharacterAnimationFactoryImpl
implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final byte characterIDSprite,
        final CharacterEventListener newCharacterEventListener
    ) {
        return new CharacterAnimationImpl(
            characterIDSprite,
            newCharacterEventListener
        );
    }
}

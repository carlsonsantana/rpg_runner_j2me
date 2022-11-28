package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.event.CharacterEventListener;

public class CharacterAnimationFactoryMock
implements CharacterAnimationFactory {
    public CharacterAnimation createCharacterAnimation(
        final byte characterIDSprite,
        final CharacterEventListener newCharacterEventListener
    ) {
        CharacterAnimationSpy characterAnimation = new CharacterAnimationSpy();
        characterAnimation.setIDSprite(characterIDSprite);
        characterAnimation.setCharacterEventListener(newCharacterEventListener);

        return characterAnimation;
    }
}

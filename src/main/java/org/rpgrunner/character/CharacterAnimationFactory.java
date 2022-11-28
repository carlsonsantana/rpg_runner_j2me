package org.rpgrunner.character;

import org.rpgrunner.event.CharacterEventListener;

public interface CharacterAnimationFactory {
    CharacterAnimation createCharacterAnimation(
        byte characterIDSprite,
        CharacterEventListener newCharacterEventListener
    );
}

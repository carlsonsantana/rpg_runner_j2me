package org.rpgrunner.character;

import org.rpgrunner.event.CharacterEventListener;

public interface CharacterAnimationFactory {
    GameCharacter createCharacterAnimation(
        byte characterIDSprite,
        CharacterEventListener newCharacterEventListener
    );
}

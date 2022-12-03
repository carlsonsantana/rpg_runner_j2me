package org.rpgrunner.character;

import org.rpgrunner.event.CharacterEventListener;

public interface CharacterFactory {
    GameCharacter createCharacter(
        byte characterIDSprite,
        CharacterEventListener newCharacterEventListener
    );
}

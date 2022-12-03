package org.rpgrunner.j2me.character;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.CharacterEventListener;

public class CharacterFactoryImpl implements CharacterFactory {
    public GameCharacter createCharacter(
        final byte characterIDSprite,
        final CharacterEventListener newCharacterEventListener
    ) {
        return new CharacterImpl(characterIDSprite, newCharacterEventListener);
    }
}

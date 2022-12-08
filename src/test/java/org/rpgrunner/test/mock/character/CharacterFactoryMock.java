package org.rpgrunner.test.mock.character;

import org.rpgrunner.character.CharacterFactory;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.event.CharacterEventListener;

public class CharacterFactoryMock implements CharacterFactory {
    public GameCharacter createCharacter(
        final byte characterIDSprite,
        final CharacterEventListener newCharacterEventListener
    ) {
        CharacterSpy character = new CharacterSpy();
        character.setCharacterEventListener(newCharacterEventListener);

        return character;
    }
}

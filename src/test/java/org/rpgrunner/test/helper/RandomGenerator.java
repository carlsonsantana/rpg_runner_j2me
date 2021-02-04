package org.rpgrunner.test.helper;

import java.util.Random;
import java.util.Vector;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.mock.character.CharacterElementSpy;
import org.rpgrunner.test.mock.character.CharacterSpy;

public class RandomGenerator {
    private static final int STRING_SIZE = 8;
    private static final int CHAR_INTERVAL_PRINTABLE_CHARACTERS = 94;
    private static final int CHAR_START_PRINTABLE_CHARACTERS = 32;
    private static final Random random = new Random();

    private RandomGenerator() { }

    public static Vector generateRandomCharacterElements() {
        GameCharacter[] characters = generateRandomCharacters();
        Vector characterElements = new Vector();

        for (int i = 0, length = characters.length; i < length; i++) {
            GameCharacter character = characters[i];
            characterElements.addElement(
                new CharacterElement(null, character, null, null)
            );
        }

        return characterElements;
    }

    private static CharacterSpy[] generateRandomCharacters() {
        Random random = new Random();
        int numberCharacters = random.nextInt(100) + 3;
        CharacterSpy[] characters = new CharacterSpy[numberCharacters];

        for (int i = 0; i < numberCharacters; i++) {
            CharacterSpy character = generateRandomCharacter();
            characters[i] = character;
        }

        return characters;
    }

    public static CharacterElement getRandomCharacterElement(
        Vector characterElements
    ) {
        Random random = new Random();
        int index = random.nextInt(characterElements.size());

        return (CharacterElement) characterElements.elementAt(index);
    }

    public static CharacterSpy generateRandomCharacter() {
        CharacterElementSpy characterElement = new CharacterElementSpy();
        String randomFileBaseName = getRandomString();
        CharacterSpy character = new CharacterSpy(randomFileBaseName);
        character.setCharacterElement(characterElement);

        return character;
    }

    public static String getRandomString() {
        char[] charArray = new char[STRING_SIZE];

        for (int i = 0; i < STRING_SIZE; i++) {
            charArray[i] = getRandomChar();
        }

        return new String(charArray);
    }

    private static char getRandomChar() {
        return (char) (
            CHAR_START_PRINTABLE_CHARACTERS
            + random.nextInt(CHAR_INTERVAL_PRINTABLE_CHARACTERS)
        );
    }

    public static byte getRandomDirection() {
        int randomNumber = random.nextInt(4);

        if (randomNumber == 0) {
            return Direction.UP;
        }

        if (randomNumber == 1) {
            return Direction.RIGHT;
        }

        if (randomNumber == 2) {
            return Direction.DOWN;
        }

        return Direction.LEFT;
    }
}

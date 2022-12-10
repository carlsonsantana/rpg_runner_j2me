package org.rpgrunner.test.helper;

import java.util.Random;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.character.movement.MovementSpy;

public class RandomGenerator {
    private static final int STRING_SIZE = 8;
    private static final int CHAR_INTERVAL_PRINTABLE_CHARACTERS = 94;
    private static final int CHAR_START_PRINTABLE_CHARACTERS = 32;
    private static final int MINIMUM_NUMBER_CHARACTERS = 3;
    private static final int MAXIMUM_NUMBER_CHARACTERS = 100;
    private static final int BYTE_MAX_VALUE = 256;
    private static final Random RANDOM = new Random();

    private RandomGenerator() { }

    public static CharacterElement[] generateRandomCharacterElements() {
        int numberCharacters = (
            RANDOM.nextInt(MAXIMUM_NUMBER_CHARACTERS)
            + MINIMUM_NUMBER_CHARACTERS
        );
        CharacterElement[] characterElements = (
            new CharacterElement[
                MAXIMUM_NUMBER_CHARACTERS + MINIMUM_NUMBER_CHARACTERS
            ]
        );

        for (int i = 0; i < numberCharacters; i++) {
            characterElements[i] = generateRandomCharacterElement();
        }

        return characterElements;
    }

    public static CharacterElement generateRandomCharacterElement() {
        MovementSpy movementSpy = new MovementSpy();
        CharacterSpy characterSpy = new CharacterSpy();
        characterSpy.setMovementCommand(movementSpy);

        return new CharacterElement(characterSpy);
    }

    public static CharacterElement getRandomCharacterElement(
        final CharacterElement[] characterElements
    ) {
        int size = 0;

        for (
            int length = characterElements.length;
            size < length;
            size++
        ) {
            if (characterElements[size] == null) {
                break;
            }
        }

        int index = RANDOM.nextInt(size);

        return characterElements[index];
    }

    public static GameCharacter getRandomCharacter(
        final GameCharacter[] characters
    ) {
        int size = 0;

        for (
            int length = characters.length;
            size < length;
            size++
        ) {
            if (characters[size] == null) {
                break;
            }
        }

        int index = RANDOM.nextInt(size);

        return characters[index];
    }

    public static GameCharacter[] generateRandomCharacters() {
        int numberCharacters = (
            RANDOM.nextInt(MAXIMUM_NUMBER_CHARACTERS)
            + MINIMUM_NUMBER_CHARACTERS
        );
        GameCharacter[] characters = (
            new GameCharacter[
                MAXIMUM_NUMBER_CHARACTERS + MINIMUM_NUMBER_CHARACTERS
            ]
        );

        for (int i = 0; i < numberCharacters; i++) {
            characters[i] = generateRandomCharacter();
        }

        return characters;
    }

    public static CharacterSpy generateRandomCharacter() {
        MovementSpy movementSpy = new MovementSpy();
        CharacterSpy character = new CharacterSpy();
        character.setMovementCommand(movementSpy);

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
            + RANDOM.nextInt(CHAR_INTERVAL_PRINTABLE_CHARACTERS)
        );
    }

    public static byte getRandomDirection() {
        int randomNumber = RANDOM.nextInt(Direction.NUMBER_DIRECTIONS);

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

    public static byte getRandomByte() {
        return (byte) RANDOM.nextInt(BYTE_MAX_VALUE);
    }

    public static int getRandomPosition() {
        return RANDOM.nextInt(BYTE_MAX_VALUE);
    }
}

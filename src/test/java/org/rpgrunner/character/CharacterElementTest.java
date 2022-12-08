package org.rpgrunner.character;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.character.movement.MovementCommand;
import org.rpgrunner.character.movement.RandomMovement;
import org.rpgrunner.test.mock.character.CharacterSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class CharacterElementTest extends TestCase {
    private CharacterElement characterElement;
    private MapHelperSpy mapHelper;
    private CharacterSpy character;
    private MovementCommand movementCommand;

    public void setUp() {
        mapHelper = new MapHelperSpy();
        character = new CharacterSpy();
        movementCommand = new RandomMovement(character, mapHelper);

        characterElement = new CharacterElement(character, movementCommand);
    }

    public void testReturnSameCharacter() {
        Assert.assertSame(character, characterElement.getCharacterAnimation());
    }

    public void testReturnSameMovementCommand() {
        Assert.assertSame(
            movementCommand,
            characterElement.getMovementCommand()
        );
    }
}

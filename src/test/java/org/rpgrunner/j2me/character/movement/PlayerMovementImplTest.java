package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.test.mock.helper.InputSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class PlayerMovementImplTest extends PlayerMovementTest {
    private final MapHelperSpy mapHelper;
    private final InputSpy input;

    public PlayerMovementImplTest() {
        mapHelper = new MapHelperSpy();
        input = new InputSpy();
    }

    protected PlayerMovement create(
        final GameCharacter character,
        final CharacterAnimation characterAnimation
    ) {
        mapHelper.resetExecuteInteractActionCalled();

        return new PlayerMovementImpl(
            character,
            characterAnimation,
            mapHelper,
            input
        );
    }

    protected MapHelperSpy getMapHelper() {
        return mapHelper;
    }

    protected InputSpy getInput() {
        return input;
    }
}

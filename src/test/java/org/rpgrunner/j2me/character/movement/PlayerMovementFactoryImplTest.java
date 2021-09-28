package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.test.mock.helper.InputSpy;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class PlayerMovementFactoryImplTest extends PlayerMovementTest {
    private final PlayerMovementFactoryImpl playerMovementFactory;
    private final MapHelperSpy mapHelper;
    private final InputSpy input;

    public PlayerMovementFactoryImplTest() {
        mapHelper = new MapHelperSpy();
        input = new InputSpy();

        playerMovementFactory = new PlayerMovementFactoryImpl(mapHelper, input);
    }

    protected PlayerMovement create(
        final GameCharacter character,
        final CharacterAnimation characterAnimation
    ) {
        mapHelper.resetExecuteInteractActionCalled();

        return playerMovementFactory.createPlayerMovement(
            character,
            characterAnimation
        );
    }

    protected MapHelperSpy getMapHelper() {
        return mapHelper;
    }

    protected InputSpy getInput() {
        return input;
    }
}

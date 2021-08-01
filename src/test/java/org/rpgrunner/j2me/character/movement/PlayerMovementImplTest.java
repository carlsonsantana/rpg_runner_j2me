package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.test.mock.helper.MapHelperSpy;

public class PlayerMovementImplTest extends PlayerMovementTest {
    private final MapHelperSpy mapHelper;

    public PlayerMovementImplTest() {
        mapHelper = new MapHelperSpy();
    }

    protected PlayerMovement create(final GameCharacter character) {
        mapHelper.resetExecuteInteractActionCalled();

        return new PlayerMovementImpl(character, mapHelper);
    }

    protected MapHelperSpy getMapHelper() {
        return mapHelper;
    }
}

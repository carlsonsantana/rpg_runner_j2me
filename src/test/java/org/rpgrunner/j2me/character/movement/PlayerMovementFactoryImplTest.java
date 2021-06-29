package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;

public class PlayerMovementFactoryImplTest extends PlayerMovementTest {
    private final PlayerMovementFactoryImpl playerMovementFactory;

    public PlayerMovementFactoryImplTest() {
        playerMovementFactory = new PlayerMovementFactoryImpl();
    }

    protected PlayerMovement create(final GameCharacter character) {
        return playerMovementFactory.createPlayerMovement(character);
    }
}

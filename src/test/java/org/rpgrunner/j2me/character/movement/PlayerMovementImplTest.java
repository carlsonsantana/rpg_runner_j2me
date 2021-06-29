package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;

public class PlayerMovementImplTest extends PlayerMovementTest {
    protected PlayerMovement create(final GameCharacter character) {
        return new PlayerMovementImpl(character);
    }
}

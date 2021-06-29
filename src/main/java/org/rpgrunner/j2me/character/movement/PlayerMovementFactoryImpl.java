package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.character.movement.PlayerMovementFactory;

public class PlayerMovementFactoryImpl implements PlayerMovementFactory {
    public PlayerMovement createPlayerMovement(
        final GameCharacter playerCharacter
    ) {
        return new PlayerMovementImpl(playerCharacter);
    }
}

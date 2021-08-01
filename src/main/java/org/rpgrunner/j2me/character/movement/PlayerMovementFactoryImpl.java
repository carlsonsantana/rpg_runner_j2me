package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.helper.MapHelper;

public class PlayerMovementFactoryImpl implements PlayerMovementFactory {
    private final MapHelper mapHelper;

    public PlayerMovementFactoryImpl(final MapHelper newMapHelper) {
        mapHelper = newMapHelper;
    }

    public PlayerMovement createPlayerMovement(
        final GameCharacter playerCharacter
    ) {
        return new PlayerMovementImpl(playerCharacter, mapHelper);
    }
}

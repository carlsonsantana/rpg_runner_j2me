package org.rpgrunner.j2me.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.helper.Input;
import org.rpgrunner.helper.MapHelper;

public class PlayerMovementFactoryImpl implements PlayerMovementFactory {
    private final MapHelper mapHelper;
    private final Input input;

    public PlayerMovementFactoryImpl(
        final MapHelper newMapHelper,
        final Input currentInput
    ) {
        mapHelper = newMapHelper;
        input = currentInput;
    }

    public PlayerMovement createPlayerMovement(
        final GameCharacter playerCharacter,
        final CharacterAnimation characterAnimation
    ) {
        return new PlayerMovementImpl(
            playerCharacter,
            characterAnimation,
            mapHelper,
            input
        );
    }
}

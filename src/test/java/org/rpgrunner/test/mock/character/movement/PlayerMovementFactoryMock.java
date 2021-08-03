package org.rpgrunner.test.mock.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.movement.PlayerMovement;
import org.rpgrunner.character.movement.PlayerMovementFactory;

public class PlayerMovementFactoryMock implements PlayerMovementFactory {
    public PlayerMovement createPlayerMovement(
        final GameCharacter playerCharacter,
        final CharacterAnimation characterAnimation
    ) {
        return new PlayerMovementMock();
    }
}

package org.rpgrunner.character.movement;

import org.rpgrunner.character.GameCharacter;

public interface PlayerMovementFactory {
    PlayerMovement createPlayerMovement(GameCharacter playerCharacter);
}

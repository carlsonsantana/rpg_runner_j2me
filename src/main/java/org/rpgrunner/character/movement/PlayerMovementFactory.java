package org.rpgrunner.character.movement;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.GameCharacter;

public interface PlayerMovementFactory {
    PlayerMovement createPlayerMovement(
        GameCharacter playerCharacter,
        CharacterAnimation characterAnimation
    );
}

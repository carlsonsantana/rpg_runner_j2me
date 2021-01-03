package org.rpgrunner.command;

import org.rpgrunner.character.GameCharacter;

public interface PlayerCommandFactory {
    PlayerCommand createPlayerCommand(GameCharacter playerCharacter);
}

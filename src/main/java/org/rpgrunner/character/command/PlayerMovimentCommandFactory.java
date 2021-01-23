package org.rpgrunner.character.command;

import org.rpgrunner.character.GameCharacter;

public interface PlayerMovimentCommandFactory {
    PlayerMovimentCommand createPlayerMovimentCommand(
        GameCharacter playerCharacter
    );
}

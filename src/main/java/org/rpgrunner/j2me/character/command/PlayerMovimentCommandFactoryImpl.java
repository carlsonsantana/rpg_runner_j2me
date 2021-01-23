package org.rpgrunner.j2me.character.command;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.command.PlayerMovimentCommand;
import org.rpgrunner.character.command.PlayerMovimentCommandFactory;

public class PlayerMovimentCommandFactoryImpl implements
        PlayerMovimentCommandFactory {
    public PlayerMovimentCommand createPlayerMovimentCommand(
        final GameCharacter playerCharacter
    ) {
        return new PlayerMovimentCommandImpl(playerCharacter);
    }
}

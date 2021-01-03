package org.rpgrunner.j2me.command;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.command.PlayerCommand;
import org.rpgrunner.command.PlayerCommandFactory;

public class PlayerCommandFactoryImpl implements PlayerCommandFactory {
    public PlayerCommand createPlayerCommand(
        final GameCharacter playerCharacter
    ) {
        return new PlayerCommandImpl(playerCharacter);
    }
}

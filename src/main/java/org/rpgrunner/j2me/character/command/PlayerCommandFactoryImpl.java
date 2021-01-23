package org.rpgrunner.j2me.character.command;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.character.command.PlayerCommand;
import org.rpgrunner.character.command.PlayerCommandFactory;

public class PlayerCommandFactoryImpl implements PlayerCommandFactory {
    public PlayerCommand createPlayerCommand(
        final GameCharacter playerCharacter
    ) {
        return new PlayerCommandImpl(playerCharacter);
    }
}

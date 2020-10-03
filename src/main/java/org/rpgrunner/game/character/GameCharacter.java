package org.rpgrunner.game.character;

public class GameCharacter {
    private final String fileBaseName;

    public GameCharacter(final String characterFileBaseName) {
        fileBaseName = characterFileBaseName;
    }

    public String getFileBaseName() {
        return fileBaseName;
    }
}

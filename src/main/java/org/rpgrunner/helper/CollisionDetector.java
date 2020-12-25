package org.rpgrunner.helper;

import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.map.Map;

public class CollisionDetector {
    private Map map;
    private GameCharacter[] characters;

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public void setCharacters(final GameCharacter[] newCharacters) {
        characters = newCharacters;
    }

    public boolean canMove(final GameCharacter character) {
        return (
            map.canMoveTo(
                character.getMapPositionX(),
                character.getMapPositionY(),
                character.getMapNextPositionX(),
                character.getMapNextPositionY(),
                character.getDirection()
            )
            && (!hasCharacterCollision(character))
        );
    }

    private boolean hasCharacterCollision(final GameCharacter character) {
        if (!character.isMoving()) {
            return true;
        }

        for (int i = 0, length = characters.length; i < length; i++) {
            GameCharacter otherCharacter = characters[i];
            if (hasCharacterCollision(character, otherCharacter)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCharacterCollision(
        final GameCharacter movingCharacter,
        final GameCharacter otherCharacter
    ) {
        return (
            (movingCharacter != otherCharacter)
            && hasSameX(movingCharacter, otherCharacter)
            && hasSameY(movingCharacter, otherCharacter)
        );
    }

    private boolean hasSameX(
        final GameCharacter movingCharacter,
        final GameCharacter otherCharacter
    ) {
        int movingCharacterX = movingCharacter.getMapNextPositionX();
        int otherCharacterX = otherCharacter.getMapPositionX();
        int otherCharacterNextX = otherCharacter.getMapNextPositionX();

        return (
            (movingCharacterX == otherCharacterX)
            || (movingCharacterX == otherCharacterNextX)
        );
    }

    private boolean hasSameY(
        final GameCharacter movingCharacter,
        final GameCharacter otherCharacter
    ) {
        int movingCharacterY = movingCharacter.getMapNextPositionY();
        int otherCharacterY = otherCharacter.getMapPositionY();
        int otherCharacterNextY = otherCharacter.getMapNextPositionY();

        return (
            (movingCharacterY == otherCharacterY)
            || (movingCharacterY == otherCharacterNextY)
        );
    }
}

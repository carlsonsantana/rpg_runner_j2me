package org.rpgrunner.helper;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.MapEventArea;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.map.Map;

public class MapHelper {
    private static final Action DEFAULT_ACTION = new NullAction();
    private final ActionQueue actionQueue;
    private final CharacterElement[] characterElements;
    private Map map;

    public MapHelper(
        final ActionQueue currentActionQueue,
        final CharacterElement[] gameCharacterElements
    ) {
        actionQueue = currentActionQueue;
        characterElements = gameCharacterElements;
    }

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public boolean canMove(final CharacterAnimation character) {
        return (
            map.canMove(character)
            && (!hasCharacterCollision(character))
        );
    }

    private boolean hasCharacterCollision(final CharacterAnimation character) {
        if (!character.isMoving()) {
            return true;
        }

        for (
            int i = 0, length = characterElements.length;
            (i < length) && (characterElements[i] != null);
            i++
        ) {
            CharacterElement otherCharacterElement = characterElements[i];
            CharacterAnimation otherCharacter = (
                otherCharacterElement.getCharacterAnimation()
            );

            if (hasCharacterCollision(character, otherCharacter)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCharacterCollision(
        final CharacterAnimation movingCharacter,
        final CharacterAnimation otherCharacter
    ) {
        return (
            (movingCharacter != otherCharacter)
            && hasSameX(movingCharacter, otherCharacter)
            && hasSameY(movingCharacter, otherCharacter)
        );
    }

    private boolean hasSameX(
        final CharacterAnimation movingCharacter,
        final CharacterAnimation otherCharacter
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
        final CharacterAnimation movingCharacter,
        final CharacterAnimation otherCharacter
    ) {
        int movingCharacterY = movingCharacter.getMapNextPositionY();
        int otherCharacterY = otherCharacter.getMapPositionY();
        int otherCharacterNextY = otherCharacter.getMapNextPositionY();

        return (
            (movingCharacterY == otherCharacterY)
            || (movingCharacterY == otherCharacterNextY)
        );
    }

    public void executeInteractAction(
        final CharacterAnimation playerCharacter
    ) {
        Action action = getInteractAction(playerCharacter);
        actionQueue.push(action);
    }

    private Action getInteractAction(final CharacterAnimation character) {
        Action actionFromCharacters = getInteractActionFromCharacters(
            character
        );

        if (actionFromCharacters != null) {
            return actionFromCharacters;
        }

        Action actionFromMapAreas = getInteractActionFromMapAreas(character);

        if (actionFromMapAreas != null) {
            return actionFromMapAreas;
        }

        return DEFAULT_ACTION;
    }

    private Action getInteractActionFromCharacters(
        final CharacterAnimation character
    ) {
        for (
            int i = 0, length = characterElements.length;
            (i < length) && (characterElements[i] != null);
            i++
        ) {
            CharacterElement otherCharacterElement = characterElements[i];
            CharacterAnimation otherCharacter = (
                otherCharacterElement.getCharacterAnimation()
            );

            if (isInFrontOfCharacter(character, otherCharacter)) {
                return otherCharacter.getInteractiveAction(
                    character.getDirection()
                );
            }
        }

        return null;
    }

    private boolean isInFrontOfCharacter(
        final CharacterAnimation referenceCharacter,
        final CharacterAnimation otherCharacter
    ) {
        return (
            (referenceCharacter != otherCharacter)
            && isInFrontOfCharacterX(referenceCharacter, otherCharacter)
            && isInFrontOfCharacterY(referenceCharacter, otherCharacter)
        );
    }

    private boolean isInFrontOfCharacterX(
        final CharacterAnimation referenceCharacter,
        final CharacterAnimation otherCharacter
    ) {
        int movingCharacterX = getInteractPositionX(referenceCharacter);
        int otherCharacterX = otherCharacter.getMapPositionX();

        return (movingCharacterX == otherCharacterX);
    }

    private boolean isInFrontOfCharacterY(
        final CharacterAnimation referenceCharacter,
        final CharacterAnimation otherCharacter
    ) {
        int movingCharacterY = getInteractPositionY(referenceCharacter);
        int otherCharacterY = otherCharacter.getMapPositionY();

        return (movingCharacterY == otherCharacterY);
    }

    private Action getInteractActionFromMapAreas(
        final CharacterAnimation character
    ) {
        int characterPositionX = getInteractPositionX(character);
        int characterPositionY = getInteractPositionY(character);

        MapEventArea[] mapEventAreas = map.getMapEventAreas();

        for (int i = 0, length = mapEventAreas.length; i < length; i++) {
            MapEventArea mapEventArea = mapEventAreas[i];

            if (
                isCharacterOnArea(
                    characterPositionX,
                    characterPositionY,
                    mapEventArea
                )
            ) {
                return mapEventArea.interact(character.getDirection());
            }
        }

        return null;
    }

    private int getInteractPositionX(final CharacterAnimation character) {
        byte direction = character.getDirection();
        int characterPositionX = character.getMapPositionX();

        if (Direction.isRight(direction)) {
            return characterPositionX + 1;
        }

        if (Direction.isLeft(direction)) {
            return characterPositionX - 1;
        }

        return characterPositionX;
    }

    private int getInteractPositionY(final CharacterAnimation character) {
        byte direction = character.getDirection();
        int characterPositionY = character.getMapPositionY();

        if (Direction.isUp(direction)) {
            return characterPositionY - 1;
        }

        if (Direction.isDown(direction)) {
            return characterPositionY + 1;
        }

        return characterPositionY;
    }

    private boolean isCharacterOnArea(
        final int interactPositionX,
        final int interactPositionY,
        final MapEventArea mapEventArea
    ) {
        int mapAreaX1 = mapEventArea.getX();
        int mapAreaX2 = mapAreaX1 + mapEventArea.getWidth();
        int mapAreaY1 = mapEventArea.getY();
        int mapAreaY2 = mapAreaY1 + mapEventArea.getHeight();

        return (
            (mapAreaX1 <= interactPositionX)
            && (mapAreaX2 >= interactPositionX)
            && (mapAreaY1 <= interactPositionY)
            && (mapAreaY2 >= interactPositionY)
        );
    }
}

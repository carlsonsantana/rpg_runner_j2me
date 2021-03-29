package org.rpgrunner.helper;

import java.util.Enumeration;
import java.util.Vector;

import org.rpgrunner.Direction;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;
import org.rpgrunner.controller.GameController;
import org.rpgrunner.controller.MapController;
import org.rpgrunner.event.ActionQueue;
import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;
import org.rpgrunner.map.Map;

public class MapHelper {
    private final GameController gameController;
    private final ActionQueue actionQueue;
    private Map map;
    private Vector characterElements;

    public MapHelper(
        final GameController currentGameController,
        final ActionQueue currentActionQueue
    ) {
        gameController = currentGameController;
        actionQueue = currentActionQueue;
    }

    public void setMap(final Map newMap) {
        map = newMap;
    }

    public void setCharacterElements(final Vector newCharacterElements) {
        characterElements = newCharacterElements;
    }

    public boolean canMove(final GameCharacter character) {
        return (
            map.canMove(character)
            && (!hasCharacterCollision(character))
        );
    }

    private boolean hasCharacterCollision(final GameCharacter character) {
        if (!character.isMoving()) {
            return true;
        }

        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement otherCharacterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            GameCharacter otherCharacter = otherCharacterElement.getCharacter();

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

    public void executeInteractAction() {
        MapController mapController = gameController.getMapController();
        GameCharacter playerCharacter = (
            mapController.getPlayerCharacterElement().getCharacter()
        );
        Action action = getInteractAction(playerCharacter);
        actionQueue.push(action);
    }

    private Action getInteractAction(final GameCharacter character) {
        for (
            Enumeration enumeration = characterElements.elements();
            enumeration.hasMoreElements();
        ) {
            CharacterElement otherCharacterElement = (
                (CharacterElement) enumeration.nextElement()
            );
            GameCharacter otherCharacter = otherCharacterElement.getCharacter();

            if (isInFrontOfCharacter(character, otherCharacter)) {
                return otherCharacter.getInteractiveAction();
            }
        }

        return new NullAction();
    }

    private boolean isInFrontOfCharacter(
        final GameCharacter referenceCharacter,
        final GameCharacter otherCharacter
    ) {
        return (
            (referenceCharacter != otherCharacter)
            && isInFrontOfCharacterX(referenceCharacter, otherCharacter)
            && isInFrontOfCharacterY(referenceCharacter, otherCharacter)
        );
    }

    private boolean isInFrontOfCharacterX(
        final GameCharacter referenceCharacter,
        final GameCharacter otherCharacter
    ) {
        byte direction = referenceCharacter.getDirection();
        int movingCharacterX;

        if (Direction.isRight(direction)) {
            movingCharacterX = referenceCharacter.getMapPositionX() + 1;
        } else if (Direction.isLeft(direction)) {
            movingCharacterX = referenceCharacter.getMapPositionX() - 1;
        } else {
            movingCharacterX = referenceCharacter.getMapPositionX();
        }

        int otherCharacterX = otherCharacter.getMapPositionX();

        return (movingCharacterX == otherCharacterX);
    }

    private boolean isInFrontOfCharacterY(
        final GameCharacter referenceCharacter,
        final GameCharacter otherCharacter
    ) {
        byte direction = referenceCharacter.getDirection();
        int otherCharacterY = otherCharacter.getMapPositionY();
        int movingCharacterY;

        if (Direction.isUp(direction)) {
            movingCharacterY = referenceCharacter.getMapPositionY() - 1;
        } else if (Direction.isDown(direction)) {
            movingCharacterY = referenceCharacter.getMapPositionY() + 1;
        } else {
            movingCharacterY = referenceCharacter.getMapPositionY();
        }

        return (movingCharacterY == otherCharacterY);
    }
}

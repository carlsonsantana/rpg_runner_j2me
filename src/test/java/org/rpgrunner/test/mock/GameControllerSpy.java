package org.rpgrunner.test.mock;

import org.rpgrunner.GameController;
import org.rpgrunner.character.CharacterAnimationFactory;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.movement.PlayerMovementFactory;
import org.rpgrunner.helper.CollisionDetector;
import org.rpgrunner.map.Map;
import org.rpgrunner.map.MapLoader;
import org.rpgrunner.test.mock.character.CharacterAnimationFactoryMock;
import org.rpgrunner.test.mock.character.movement.PlayerMovementFactoryMock;
import org.rpgrunner.test.mock.event.factory.ActionAbstractFactorySpy;

public class GameControllerSpy extends GameController {
    private Map map;
    private int countMapChanged;
    private CharacterElement playerCharacterElement;
    private CharacterElement lastCharacterElementAdded;
    private ActionAbstractFactorySpy actionAbstractFactory;

    public GameControllerSpy() {
        super(null, null, null);
        map = null;
        countMapChanged = 0;
        actionAbstractFactory = new ActionAbstractFactorySpy();
    }

    public void setMap(final Map newMap) {
        map = newMap;
        countMapChanged++;
    }

    public Map getMap() {
        return map;
    }

    public int getCountMapChanged() {
        return countMapChanged;
    }

    public void setPlayerCharacterElement(
        final CharacterElement newPlayerCharacterElement
    ) {
        playerCharacterElement = newPlayerCharacterElement;
    }

    public CharacterElement getPlayerCharacterElement() {
        return playerCharacterElement;
    }

    public PlayerMovementFactory getPlayerMovementFactory() {
        return new PlayerMovementFactoryMock();
    }

    public CharacterAnimationFactory getCharacterAnimationFactory() {
        return new CharacterAnimationFactoryMock();
    }

    public CollisionDetector getCollisionDetector() {
        return new CollisionDetector();
    }

    public void addCharacterElement(final CharacterElement characterElement) {
        lastCharacterElementAdded = characterElement;
    }

    public CharacterElement getLastCharacterElementAdded() {
        return lastCharacterElementAdded;
    }

    public MapLoader getMapLoader() {
        return new MapLoader(actionAbstractFactory);
    }
}

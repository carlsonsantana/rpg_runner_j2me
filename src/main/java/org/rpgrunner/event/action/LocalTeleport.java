package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;

public class LocalTeleport implements Action {
    private final int mapPositionX;
    private final int mapPositionY;
    private CharacterElement characterElement;

    public LocalTeleport(
        final int toMapPositionX,
        final int toMapPositionY
    ) {
        mapPositionX = toMapPositionX;
        mapPositionY = toMapPositionY;
    }

    public void setCharacterElement(
        final CharacterElement newCharacterElement
    ) {
        characterElement = newCharacterElement;
    }

    public void execute() {
        updateCharacterMapPosition();
        updateCharacterAnimationMapPosition();
    }

    private void updateCharacterMapPosition() {
        GameCharacter character = characterElement.getCharacterAnimation();
        character.setMapPosition(mapPositionX, mapPositionY);
    }

    private void updateCharacterAnimationMapPosition() {
        GameCharacter characterAnimation = (
            characterElement.getCharacterAnimation()
        );
        characterAnimation.updateScreenPositionFromMapPosition();
    }
}

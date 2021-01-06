package org.rpgrunner.event.action;

import org.rpgrunner.character.CharacterAnimation;
import org.rpgrunner.character.CharacterElement;
import org.rpgrunner.character.GameCharacter;

public class LocalTeleport {
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

    public void call() {
        updateCharacterMapPosition();
        updateCharacterAnimationMapPosition();
    }

    private void updateCharacterMapPosition() {
        GameCharacter character = characterElement.getCharacter();
        character.setMapPosition(mapPositionX, mapPositionY);
    }

    private void updateCharacterAnimationMapPosition() {
        CharacterAnimation characterAnimation = (
            characterElement.getCharacterAnimation()
        );
        characterAnimation.updateScreenPositionFromMapPosition();
    }
}

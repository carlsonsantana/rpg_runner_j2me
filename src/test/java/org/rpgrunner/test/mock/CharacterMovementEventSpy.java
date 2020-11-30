package org.rpgrunner.test.mock;

import org.rpgrunner.game.character.CharacterElement;
import org.rpgrunner.game.character.CharacterMovimentEvent;

public class CharacterMovementEventSpy extends CharacterMovimentEvent {
    private boolean onMoveValue;

    public CharacterMovementEventSpy(final boolean onMoveReturn) {
        super(null);
        onMoveValue = onMoveReturn;
    }

    public boolean onMove(final CharacterElement characterElement) {
        return onMoveValue;
    }
}

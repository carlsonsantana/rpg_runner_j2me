package org.rpgrunner.game.j2me;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.rpgrunner.game.Direction;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterRender;

public class CharacterRenderImpl implements CharacterRender {
    private static final String CHARACTER_DIRECTORY = "/character/";
    private static final String CHARACTER_EXTENSION = ".png";

    private static final short SPRITE_HEIGHT = 32;
    private static final short SPRITE_WIDTH = 16;

    private static final short SPRITE_FRAME_UP_WALKING_1 = 0;
    private static final short SPRITE_FRAME_UP_STOPPED = 1;
    private static final short SPRITE_FRAME_UP_WALKING_2 = 2;
    private static final short SPRITE_FRAME_RIGHT_WALKING_1 = 3;
    private static final short SPRITE_FRAME_RIGHT_STOPPED = 4;
    private static final short SPRITE_FRAME_RIGHT_WALKING_2 = 5;
    private static final short SPRITE_FRAME_DOWN_WALKING_1 = 6;
    private static final short SPRITE_FRAME_DOWN_STOPPED = 7;
    private static final short SPRITE_FRAME_DOWN_WALKING_2 = 8;
    private static final short SPRITE_FRAME_LEFT_WALKING_1 = 9;
    private static final short SPRITE_FRAME_LEFT_STOPPED = 10;
    private static final short SPRITE_FRAME_LEFT_WALKING_2 = 11;

    private static final short SPRITE_INITIAL = SPRITE_FRAME_DOWN_STOPPED;

    private static final int[] SPRITE_ANIMATION_UP = {
        SPRITE_FRAME_UP_WALKING_1,
        SPRITE_FRAME_UP_STOPPED,
        SPRITE_FRAME_UP_WALKING_2,
        SPRITE_FRAME_UP_STOPPED
    };
    private static final int[] SPRITE_ANIMATION_RIGHT = {
        SPRITE_FRAME_RIGHT_WALKING_1,
        SPRITE_FRAME_RIGHT_STOPPED,
        SPRITE_FRAME_RIGHT_WALKING_2,
        SPRITE_FRAME_RIGHT_STOPPED
    };
    private static final int[] SPRITE_ANIMATION_DOWN = {
        SPRITE_FRAME_DOWN_WALKING_1,
        SPRITE_FRAME_DOWN_STOPPED,
        SPRITE_FRAME_DOWN_WALKING_2,
        SPRITE_FRAME_DOWN_STOPPED
    };
    private static final int[] SPRITE_ANIMATION_LEFT = {
        SPRITE_FRAME_LEFT_WALKING_1,
        SPRITE_FRAME_LEFT_STOPPED,
        SPRITE_FRAME_LEFT_WALKING_2,
        SPRITE_FRAME_LEFT_STOPPED
    };

    private final Graphics graphics;
    private final Sprite sprite;
    private final GameCharacter character;
    private byte direction;

    public CharacterRenderImpl(
        final Graphics gameGraphics,
        final GameCharacter gameCharacter
    ) {
        graphics = gameGraphics;
        character = gameCharacter;
        sprite = new Sprite(loadImage(), SPRITE_WIDTH, SPRITE_HEIGHT);
        sprite.setFrame(SPRITE_INITIAL);
    }

    private Image loadImage() {
        String fileName = (
            CHARACTER_DIRECTORY
            + character.getFileBaseName()
            + CHARACTER_EXTENSION
        );

        try {
            return Image.createImage(fileName);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void render() {
        changeSpriteAnimation();
        changeSpriteFrame();

        sprite.paint(graphics);
    }

    private void changeSpriteAnimation() {
        if (direction != character.getDirection()) {
            direction = character.getDirection();
            int[] animation;
            if (Direction.isUp(direction)) {
                animation = SPRITE_ANIMATION_UP;
            } else if (Direction.isRight(direction)) {
                animation = SPRITE_ANIMATION_RIGHT;
            } else if (Direction.isDown(direction)) {
                animation = SPRITE_ANIMATION_DOWN;
            } else {
                animation = SPRITE_ANIMATION_LEFT;
            }

            sprite.setFrameSequence(animation);
        }
    }

    private void changeSpriteFrame() {
        int currentFrame = sprite.getFrame();
        if (
            character.isMoving()
            || (
                currentFrame != SPRITE_FRAME_UP_STOPPED
                && currentFrame != SPRITE_FRAME_RIGHT_STOPPED
                && currentFrame != SPRITE_FRAME_DOWN_STOPPED
                && currentFrame != SPRITE_FRAME_LEFT_STOPPED
            )
        ) {
            sprite.nextFrame();
        }
    }
}

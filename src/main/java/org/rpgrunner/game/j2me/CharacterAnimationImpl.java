package org.rpgrunner.game.j2me;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.rpgrunner.game.Direction;
import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterAnimation;

public class CharacterAnimationImpl implements CharacterAnimation {
    private static final String CHARACTER_DIRECTORY = "/characters/";
    private static final String CHARACTER_EXTENSION = ".png";

    private static final short SPRITE_HEIGHT = 32;
    private static final short SPRITE_WIDTH = 16;
    private static final short SPRITE_MOVE_SPEED = 4;
    private static final short SPRITE_REFERENCE_Y = (
        SPRITE_HEIGHT - TileSetRender.TILE_HEIGHT
    );

    private static final short SPRITE_FRAME_WALKING_1 = 0;
    private static final short SPRITE_FRAME_STOPPED_1 = 1;
    private static final short SPRITE_FRAME_WALKING_2 = 2;
    private static final short SPRITE_FRAME_STOPPED_2 = 3;

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

    private static final short SPRITE_INITIAL = SPRITE_FRAME_STOPPED_2;

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

    private final Sprite sprite;
    private final GameCharacter character;
    private byte direction;

    public CharacterAnimationImpl(final GameCharacter gameCharacter) {
        character = gameCharacter;
        sprite = new Sprite(loadImage(), SPRITE_WIDTH, SPRITE_HEIGHT);
        sprite.defineReferencePixel(0, SPRITE_REFERENCE_Y);
        sprite.setPosition(0, -SPRITE_REFERENCE_Y);
        changeSpriteAnimation();
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

    public int getScreenX() {
        return sprite.getX();
    }

    public int getScreenY() {
        return sprite.getY();
    }

    public int getWidth() {
        return SPRITE_WIDTH;
    }

    public int getHeight() {
        return SPRITE_HEIGHT;
    }

    public void setScreenPosition(final int screenX, final int screenY) {
        sprite.setPosition(screenX, screenY);
    }

    public void startAnimation() {
        if (isAnimationComplete()) {
            changeSpriteAnimation();
        }
        moveSprite();
        changeSpriteFrame();
    }

    public Object getSprite() {
        return sprite;
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
            sprite.setFrame(SPRITE_INITIAL);
        }
    }

    private void changeSpriteFrame() {
        if (isAnimationRunning()) {
            sprite.nextFrame();
        }
    }

    private void moveSprite() {
        if (isAnimationRunning()) {
            int x = sprite.getX();
            int y = sprite.getY();
            if (Direction.isUp(direction)) {
                y -= SPRITE_MOVE_SPEED;
            } else if (Direction.isRight(direction)) {
                x += SPRITE_MOVE_SPEED;
            } else if (Direction.isDown(direction)) {
                y += SPRITE_MOVE_SPEED;
            } else {
                x -= SPRITE_MOVE_SPEED;
            }
            sprite.setPosition(x, y);
        }
    }

    private boolean isAnimationRunning() {
        return character.isMoving() || (!isAnimationComplete());
    }

    public boolean isAnimationComplete() {
        int currentFrame = sprite.getFrame();
        return currentFrame == SPRITE_FRAME_STOPPED_2;
    }

    public GameCharacter getCharacter() {
        return character;
    }
}

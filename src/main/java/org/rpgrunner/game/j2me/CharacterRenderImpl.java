package org.rpgrunner.game.j2me;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.rpgrunner.game.character.GameCharacter;
import org.rpgrunner.game.character.CharacterRender;

public class CharacterRenderImpl implements CharacterRender {
    private static final String CHARACTER_DIRECTORY = "/character/";
    private static final String CHARACTER_EXTENSION = ".png";

    private static final short SPRITE_HEIGHT = 32;
    private static final short SPRITE_WIDTH = 16;

    private static final short SPRITE_WALKING_UP_1 = 0;
    private static final short SPRITE_WALKING_UP_STOPPED = 1;
    private static final short SPRITE_WALKING_UP_2 = 2;
    private static final short SPRITE_WALKING_RIGHT_1 = 3;
    private static final short SPRITE_WALKING_RIGHT_STOPPED = 4;
    private static final short SPRITE_WALKING_RIGHT_2 = 5;
    private static final short SPRITE_WALKING_DOWN_1 = 6;
    private static final short SPRITE_WALKING_DOWN_STOPPED = 7;
    private static final short SPRITE_WALKING_DOWN_2 = 8;
    private static final short SPRITE_WALKING_LEFT_1 = 9;
    private static final short SPRITE_WALKING_LEFT_STOPPED = 10;
    private static final short SPRITE_WALKING_LEFT_2 = 11;

    private static final short SPRITE_INITIAL = SPRITE_WALKING_DOWN_STOPPED;

    private final Graphics graphics;
    private final Sprite sprite;
    private final GameCharacter character;

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
        sprite.paint(graphics);
    }
}

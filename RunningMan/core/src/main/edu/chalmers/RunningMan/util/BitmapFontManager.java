package edu.chalmers.RunningMan.util;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * A class which can create a group of bitmap fonts
 */
public class BitmapFontManager {

    private static BitmapFont menuButtonFontBlack, menuButtonFontWhite;
    private int fontSize = 50;

    public BitmapFontManager() {
        createFonts();
    }

    private void createFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/StarFont.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = Color.BLACK;
        menuButtonFontBlack = generator.generateFont(parameter);

        parameter.color = Color.WHITE;
        menuButtonFontWhite = generator.generateFont(parameter);

        // if more fonts, create them here

        generator.dispose();
    }

    public BitmapFont createNewMenuButtonFontBlack() {
        return menuButtonFontBlack;
    }

    public BitmapFont createNewMenuButtonFontWhite() {
        return menuButtonFontWhite;
    }
}

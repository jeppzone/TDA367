package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Jesper on 5/15/2015.
 */
public class HudFont {
    protected BitmapFont font;
    FreeTypeFontGenerator generator;

    public void generateFont(){
        try {
             generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/fonts/StarFont.TTF"));
        }catch (Exception e){
            throw new NullPointerException("Could not find font in HudFont");
        }
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
    }
}

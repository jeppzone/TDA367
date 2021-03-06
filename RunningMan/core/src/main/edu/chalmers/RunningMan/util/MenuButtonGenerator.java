package edu.chalmers.RunningMan.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * A class which generates a menu button of a certain style
 */
public class MenuButtonGenerator {

    private final BitmapFont bitmapFont;
    private final Skin skin;
    private final TextureAtlas atlas;
    private TextButtonStyle textButtonStyle;
    private final TextButton textButton;

    private static final String ATLAS_PATH = "mainmenu/mainmenu_buttonsheet.txt";

    public MenuButtonGenerator(String text, BitmapFont bitmapFont) {

        this.bitmapFont = bitmapFont;
        // contains default atlas
        this.atlas = new TextureAtlas(ATLAS_PATH);
        this.skin = new Skin(atlas);
        createButtonStyle();
        this.textButton = new TextButton(text, textButtonStyle);
    }

    /**
     * Create fonts
     * @precon needs the skin drawable to have button up image name set to "buttonup" and
     * the button down image set to "buttondown".
     */
    private void createButtonStyle() {
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("buttonup");
        textButtonStyle.down = skin.getDrawable("buttondown");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = bitmapFont;
    }

    public TextButton createNewTextButton() {
        TextButton newButton = new TextButton(String.valueOf(textButton), textButton.getStyle());

        // adds a little distance around the text to the button edge
        newButton.pad(20);

        return newButton;
    }
}

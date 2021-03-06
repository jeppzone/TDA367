package edu.chalmers.RunningMan.screen.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chalmers.RunningMan.util.BitmapFontManager;
import edu.chalmers.RunningMan.util.MenuButtonGenerator;

/**
 * A class for the main menu view
 */
public class MainMenuView extends Stage {

    private final TextureAtlas atlas;
    private final Skin skin;
    private final Table table;
    private TextButton buttonPlay, buttonExit;
    private BitmapFont whiteFont, blackFont;
    private Label heading;

    private final BitmapFontManager bitmapFontManager = new BitmapFontManager();
    private MenuButtonGenerator menuButtonGenerator;

    private static final String ATLAS_PATH = "mainmenu/mainmenu_buttonsheet.txt";

    // only for debugging
    private static final boolean DEBUG = false;

    public MainMenuView () {

        atlas = new TextureAtlas(ATLAS_PATH);

        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        createFonts();

        createButtons();

        createHeading();

        // add to table
        table.add(heading);
        table.getCell(heading).spaceBottom(30);
        table.row();
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(15);
        table.row();
        table.add(buttonExit);

        if (DEBUG) {
            table.debug();
        }

        // add to stage
        this.addActor(table);

    }

    /**
     * Create buttons
     */
    private void createButtons() {
        menuButtonGenerator = new MenuButtonGenerator("", blackFont);
        buttonPlay = menuButtonGenerator.createNewTextButton();
        buttonPlay.setText("PLAY");
        buttonExit = menuButtonGenerator.createNewTextButton();
        buttonExit.setText("EXIT");
    }

    /**
     * Create bitmap fonts
     */
    private void createFonts() {
        blackFont = bitmapFontManager.createNewMenuButtonFontBlack();
        blackFont.setColor(Color.BLACK);
        whiteFont = bitmapFontManager.createNewMenuButtonFontWhite();
        whiteFont.setColor(Color.WHITE);
    }

    /**
     * Create heading
     */
    private void createHeading() {
        heading = new Label("RunningMan", new Label.LabelStyle(whiteFont, Color.WHITE));
    }

    public void render(float delta) {
        this.act(delta);
        this.draw();
    }

    public void addListener(ClickListener listener) {
        buttonPlay.addListener(listener);
        buttonExit.addListener(listener);
    }
}

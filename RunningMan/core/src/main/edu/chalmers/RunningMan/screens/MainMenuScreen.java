package edu.chalmers.RunningMan.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A screen class which handles the main menu
 */
public class MainMenuScreen implements IScreen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit;
    private BitmapFont whiteFont, blackFont;
    private Label heading;
    private PropertyChangeSupport pcs;

    private BitmapFontManager bitmapFontManager = new BitmapFontManager();
    private MenuButtonGenerator menuButtonGenerator;

    private static final String ATLAS_PATH = "core/assets/mainmenu/mainmenu_buttonsheet.txt";

    // only for debugging
    private static final boolean DEBUG = false;

    public MainMenuScreen() {
        super();
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

        stage = new Stage();

        // set input processor so buttons can be pressed by cursor click
        Gdx.input.setInputProcessor(stage);

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
        stage.addActor(table);
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
        heading = new Label("RunningMan", new LabelStyle(whiteFont, Color.WHITE));
    }

    public void	addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        clearScreen();

        if(buttonExit.isPressed()) {
            Gdx.app.exit();
        }

        if(buttonPlay.isPressed()) {
            pcs.firePropertyChange("loadScreen", null, null);
        }

        stage.act(delta);

        stage.draw();
    }

    private void clearScreen() {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        whiteFont.dispose();
        blackFont.dispose();
        skin.dispose();
    }
}

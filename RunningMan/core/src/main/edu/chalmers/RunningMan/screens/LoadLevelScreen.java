package edu.chalmers.RunningMan.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A screen class which handles the loading level menu
 */
public class LoadLevelScreen implements IScreen {

    private Stage stage;
    //private TextureAtlas atlas = new TextureAtlas("core/assetes/loadLevelMenu/loadlevelmenu_buttonsheet");
    private TextureAtlas atlas = new TextureAtlas("core/assets/mainmenu/mainmenu_buttonsheet.txt");
    private Skin skin;
    private Table table;
    private TextButton buttonLevel1, buttonLevel2;
    private BitmapFont blackFont, whiteFont;
    private Label heading;
    private TextButtonStyle textButtonStyle;
    private PropertyChangeSupport pcs;

    public LoadLevelScreen() {
        super();
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        stage = new Stage();
        // input processor so buttons can be pressed
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        createFonts();

        createButtonStyle();

        createButtons();

        createHeading();

        // add to table
        table.add(heading);
        table.getCell(heading).spaceBottom(30);
        table.row();
        table.add(buttonLevel1);
        table.getCell(buttonLevel1).spaceBottom(15);
        table.row();
        table.add(buttonLevel2);
        //table.debug();

        // add to stage
        stage.addActor(table);
    }

    /**
     * Create fonts
     */
    private void createFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/fonts/StarFont.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        parameter.color = Color.BLACK;
        blackFont = generator.generateFont(parameter);
        parameter.color = Color.WHITE;
        whiteFont = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Create button style
     */
    private void createButtonStyle() {
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("buttonup");
        textButtonStyle.down = skin.getDrawable("buttondown");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = blackFont;
    }

    /**
     * Create buttons
     */
    private void createButtons() {
        buttonLevel1 = new TextButton("1", textButtonStyle);
        buttonLevel1.addListener(new ClickListener());
        buttonLevel1.pad(20);

        buttonLevel2 = new TextButton("2", textButtonStyle);
        buttonLevel2.addListener(new ClickListener());
        buttonLevel2.pad(20);
    }

    /**
     * Create heading
     */
    private void createHeading() {
        heading = new Label("Levels", new Label.LabelStyle(whiteFont, Color.WHITE));
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        clearScreen();

        if(buttonLevel1.isPressed()) {
            pcs.firePropertyChange("level1", null, null);
        }

        if(buttonLevel2.isPressed()) {
            pcs.firePropertyChange("level2", null, null);
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

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

}


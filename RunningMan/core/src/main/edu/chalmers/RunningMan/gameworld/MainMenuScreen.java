package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chalmers.RunningMan.RunningMan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Kvist1 on 2015-05-14.
 */
public class MainMenuScreen implements Screen {

    private Stage stage;
    private TextureAtlas atlas = new TextureAtlas("core/assets/mainmenu/mainmenu_buttonsheet.txt");
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit;
    private BitmapFont whiteFont, blackFont;
    private Label heading;
    private PropertyChangeSupport pcs;
    private TextButtonStyle textButtonPlayStyle, textButtonExitStyle;

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
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(15);
        table.row();
        table.add(buttonExit);
        //table.debug();

        // add to stage
        stage.addActor(table);
    }

    /**
     * Create fonts
     */
    private void createFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/fonts/StarFont.TTF"));
        FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        parameter.color = Color.BLACK;
        blackFont = generator.generateFont(parameter); // font size 12 pixels
        parameter.color = Color.WHITE;
        whiteFont = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Create button style
     */
    private void createButtonStyle() {
        textButtonPlayStyle = new TextButtonStyle();
        textButtonPlayStyle.up = skin.getDrawable("buttonup");
        textButtonPlayStyle.down = skin.getDrawable("buttondown"); // TODO change to buttonExit down picture
        textButtonPlayStyle.pressedOffsetX = 1;
        textButtonPlayStyle.pressedOffsetY = -1;
        textButtonPlayStyle.font = blackFont;

        textButtonExitStyle = new TextButtonStyle();
        textButtonExitStyle.up = skin.getDrawable("buttonup");
        textButtonExitStyle.down = skin.getDrawable("buttonup"); // TODO change to buttonExit down picture
        textButtonExitStyle.pressedOffsetX = 1;
        textButtonExitStyle.pressedOffsetY = -1;
        textButtonExitStyle.font = blackFont;
    }

    /**
     * Create buttons
     */
    private void createButtons() {
        buttonPlay = new TextButton("PLAY", textButtonPlayStyle);
        buttonPlay.addListener(new ClickListener());
        buttonPlay.pad(20);

        buttonExit = new TextButton("EXIT", textButtonExitStyle);
        buttonExit.addListener(new ClickListener());
        buttonExit.pad(20);
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
            pcs.firePropertyChange("game", null, null);
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

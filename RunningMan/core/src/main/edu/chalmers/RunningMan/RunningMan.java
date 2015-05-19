package edu.chalmers.RunningMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.controllers.AudioController;
import edu.chalmers.RunningMan.gameworld.LoadLevelScreen;
import edu.chalmers.RunningMan.gameworld.MainMenuScreen;
import edu.chalmers.RunningMan.utils.InputProcessor;
import edu.chalmers.RunningMan.gameworld.GameScreen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RunningMan extends Game implements PropertyChangeListener {

    public static final String TITLE = "RunningMan";
    public static final int SCALE = 3;

    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;
    private LoadLevelScreen loadLevelScreen;

	@Override
	public void create () {

        Gdx.app.log("RunningMan Game", "created");
        createGameScreen();
        createLoadLevelingScreen();
        createMainMenuScreen();

        setScreen(mainMenuScreen);
	}

    private void createGameScreen() {
        gameScreen = new GameScreen();
        gameScreen.addPropertyChangeListener(this);
    }

    private void createLoadLevelingScreen() {
        loadLevelScreen = new LoadLevelScreen();
        loadLevelScreen.addPropertyChangeListener(this);
    }

    private void createMainMenuScreen() {
        mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String eventName = evt.getPropertyName();
        if(eventName.equals("loadScreen")) {
            setScreen(loadLevelScreen);

        }else if(eventName.equals("time") || eventName.equals("dead")) {
            setScreen(mainMenuScreen);

        }else if(eventName.equals("finish")) {
            setScreen(mainMenuScreen);// change to highscorescreen later

        } else if(eventName.equals("level1")) {
            gameScreen.createWorld("level1");
            setScreen(gameScreen);

        } else if(eventName.equals("level2")) {
            gameScreen.createWorld("level2");
            setScreen(gameScreen);
        }
    }
}

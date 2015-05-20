package edu.chalmers.RunningMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import edu.chalmers.RunningMan.gameworld.LoadLevelScreen;
import edu.chalmers.RunningMan.gameworld.HighScoreScreen;
import edu.chalmers.RunningMan.gameworld.MainMenuScreen;
import edu.chalmers.RunningMan.gameworld.GameScreen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RunningMan extends Game implements PropertyChangeListener {

    public static final String TITLE = "RunningMan";
    public static final int SCALE = 3;
    
    private LoadLevelScreen loadLevelScreen;
    public GameScreen gameScreen;
    public MainMenuScreen mainMenuScreen;
    private HighScoreScreen highScoreScreen;

	@Override
	public void create () {

        Gdx.app.log("RunningMan Game", "created");
        //createGameScreen();
        createLoadLevelingScreen();
        createMainMenuScreen();

        setScreen(mainMenuScreen);
	}

    private void createGameScreen(String level) {
        gameScreen = new GameScreen(level);
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

    private void createHighScoreScreen(){
        highScoreScreen = new HighScoreScreen();
        highScoreScreen.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String eventName = evt.getPropertyName();
        if(eventName.equals("loadScreen")) {
            setScreen(loadLevelScreen);

        } else if(eventName.equals("time") || eventName.equals("dead")) {
            setScreen(mainMenuScreen);

        } else if(eventName.equals("level1")) {
            //gameScreen.createWorld("level1");
            createGameScreen("level1");
            setScreen(gameScreen);

        } else if(eventName.equals("level2")) {
            //gameScreen.createWorld("level2");
            createGameScreen("level2");
            setScreen(gameScreen);

        } else if(eventName.equals("finish")) {
            createHighScoreScreen();
            setScreen(mainMenuScreen);
        }
    }
}

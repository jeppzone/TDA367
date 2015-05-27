package edu.chalmers.RunningMan;

import com.badlogic.gdx.Game;
import edu.chalmers.RunningMan.screen.*;
import edu.chalmers.RunningMan.screen.highscoremenu.HighScoreScreen;
import edu.chalmers.RunningMan.screen.loadlevelmenu.LoadLevelMenuScreen;
import edu.chalmers.RunningMan.screen.mainmenu.MainMenuScreen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RunningMan extends Game implements PropertyChangeListener {

    public static final String TITLE = "RunningMan";
    public static final int SCALE = 3;
    
    private LoadLevelMenuScreen loadLevelMenuScreen;
    private GameScreen gameScreen;
    private HighScoreScreen highScoreScreen;

    private MainMenuScreen mainMenuScreen;

	@Override
	public void create () {

        //createGameScreen();
        createLoadLevelMenuScreen();
        createMainMenuScreen();

        setScreen(mainMenuScreen);
	}

    private void createGameScreen(String level) {
        gameScreen = new GameScreen(level);
        gameScreen.addPropertyChangeListener(this);
    }

    private void createLoadLevelMenuScreen() {
        loadLevelMenuScreen = new LoadLevelMenuScreen();
        loadLevelMenuScreen.addPropertyChangeListener(this);
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
            setScreen(loadLevelMenuScreen);

        } else if(eventName.equals("mainMenu")) {
            setScreen(mainMenuScreen);

        } else if(eventName.equals("level1")) {
            createGameScreen(eventName);
            setScreen(gameScreen);

        } else if(eventName.equals("level2")) {
            createGameScreen(eventName);
            setScreen(gameScreen);

        } else if(eventName.equals("showHighScore")) {
            createHighScoreScreen();
            setScreen(highScoreScreen);
        }
    }
}

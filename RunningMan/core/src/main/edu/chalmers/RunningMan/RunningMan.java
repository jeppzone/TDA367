package edu.chalmers.RunningMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.gameworld.MainMenuScreen;
import edu.chalmers.RunningMan.utils.InputProcessor;
import edu.chalmers.RunningMan.gameworld.GameScreen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RunningMan extends Game implements PropertyChangeListener {

    public static final String TITLE = "RunningMan";
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    private float accum;

    public GameScreen gameScreen;
    public MainMenuScreen mainMenuScreen;

	@Override
	public void create () {

        Gdx.app.log("RunningMan Game", "created");

        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        mainMenuScreen.addPropertyChangeListener(this);

        setScreen(mainMenuScreen);


	}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("EVEEEENT");
        String eventName = evt.getPropertyName();
        if(eventName.equals("game")){
            setScreen(gameScreen);
        }
    }
}

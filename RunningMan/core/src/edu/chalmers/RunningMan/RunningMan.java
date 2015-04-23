package edu.chalmers.RunningMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.handlers.InputProcessor;
import edu.chalmers.RunningMan.screens.GameScreen;

public class RunningMan extends Game {

    public static final String TITLE = "RunningMan";
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    private float accum;

    private SpriteBatch batch;
    private OrthographicCamera cam;


	@Override
	public void create () {

        Gdx.app.log("ZBGame", "created");

        Gdx.input.setInputProcessor(new InputProcessor());

        // TODO set screen to menu when menu is implemented
        setScreen(new GameScreen());

	}


    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return cam;
    }
}

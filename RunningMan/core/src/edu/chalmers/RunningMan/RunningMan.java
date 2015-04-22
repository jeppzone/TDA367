package edu.chalmers.RunningMan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.handlers.GameStateManager;
import edu.chalmers.RunningMan.screens.GameScreen;
import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;

public class RunningMan extends Game {

    public static final String TITLE = "RunningMan";
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    private float accum;

    private SpriteBatch batch;
    private OrthographicCamera cam;

    private GameStateManager gsm;

	@Override
	public void create () {

        Gdx.app.log("ZBGame", "created");

        // TODO set screen to menu when menu is implemented
        setScreen(new GameScreen());

        /** // Gammalt
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        gsm = new GameStateManager(this);
         */

	}

	//@Override
	//public void render () {
        /**
        // the time span between the current frame and the last frame (in seconds)
        accum += Gdx.graphics.getDeltaTime();

        // only render and update if accumulated time is > STEP
        while(accum >= STEP) {
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }
         */
	//}


    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return cam;
    }
}

package edu.chalmers.RunningMan.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.handlers.GameStateManager;

/**
 * Abstract class for all game states
 */
public abstract class GameState {

    protected  GameStateManager gsm;
    protected RunningMan game;

    protected SpriteBatch batch;
    protected OrthographicCamera cam;

    protected GameState(GameStateManager gsm) {
        this.gsm =gsm;
        game = gsm.game();
        batch = game.getSpriteBatch();
        cam = game.getCamera();
    }

    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render();
    public abstract void dispose();
}

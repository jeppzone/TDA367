package edu.chalmers.RunningMan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import edu.chalmers.RunningMan.handlers.GameStateManager;

/**
 * A class for the game state Play
 */
public class Play extends GameState {

    private BitmapFont font = new BitmapFont();

    public Play(GameStateManager gsm){
        super(gsm);
    }

    public void handleInput() {

    }

    public void update(float deltaTime) {

    }

    public void render() {

        // clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        font.draw(batch, "play state", 100, 100);
        batch.end();
    }

    public void dispose() {

    }
}

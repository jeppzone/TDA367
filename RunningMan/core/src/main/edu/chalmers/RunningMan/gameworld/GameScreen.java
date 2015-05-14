package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.gameworld.GameWorld;
import edu.chalmers.RunningMan.utils.InputProcessor;

/**
 * Created by Kvist1 on 2015-04-22.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private RunningMan game;

    public GameScreen(RunningMan game) {
        //Gdx.app.log("GameScreen", "Attached");
        super();
        this.game = game;
        world = new GameWorld();
    }

    @Override
    public void render(float deltaTime) {

        // clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update world
        world.update(deltaTime);

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
        Gdx.input.setInputProcessor(new InputProcessor());

    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}

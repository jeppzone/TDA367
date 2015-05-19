package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.controllers.AudioController;
import edu.chalmers.RunningMan.gameworld.GameWorld;
import edu.chalmers.RunningMan.utils.InputProcessor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A screen class which handles the game
 */
public class GameScreen implements IScreen, PropertyChangeListener {

    private PropertyChangeSupport pcs;
    private GameWorld world;
    private AudioController audioController;
    private String level;

    public GameScreen(String level) {
        super();
        this.level = level;
        pcs = new PropertyChangeSupport(this);
        world = new GameWorld(level);
        world.addPropertyChangeListener(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(this);
    }

    /*
    public void createWorld(String level) {
        world = new GameWorld(level);
        world.addPropertyChangeListener(this);
    }
    */

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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt.getPropertyName(), null, null);
        final String eventName = evt.getPropertyName();
        if (eventName.equals("dead") || eventName.equals("finish") || eventName.equals("time")) {
            world = new GameWorld(level);
            world.addPropertyChangeListener(this);
        }
    }
}

package edu.chalmers.RunningMan.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.controllers.GameController;
import edu.chalmers.RunningMan.utils.input.InputProcessor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A screen class which handles the game
 */
public class GameScreen implements IScreen, PropertyChangeListener {

    private PropertyChangeSupport pcs;
    private GameController world;
    private String level;

    public GameScreen(String level) {
        super();
        this.level = level;
        pcs = new PropertyChangeSupport(this);
        world = new GameController(level);
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
        world = new GameController(level);
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
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputProcessor());

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt.getPropertyName(), null, null);

    }
}

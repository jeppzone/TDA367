package edu.chalmers.RunningMan.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.controller.GameController;
import edu.chalmers.RunningMan.util.input.InputProcessor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A screen class which handles the game
 */
public class GameScreen implements Screen, PropertyChangeListener {

    private final PropertyChangeSupport pcs;
    private final GameController gameController;

    public GameScreen(String level) {
        super();
        pcs = new PropertyChangeSupport(this);
        gameController = new GameController(level);
        gameController.addPropertyChangeListener(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void render(float deltaTime) {
        // clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // update world
        gameController.update(deltaTime);

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

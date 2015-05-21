
package edu.chalmers.RunningMan.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.gameworld.Factory;
import edu.chalmers.RunningMan.gameworld.GameWorld;
import edu.chalmers.RunningMan.utils.HighScore.HighScore;
import edu.chalmers.RunningMan.utils.HighScore.HighScoreView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class HighScoreScreen implements Screen, PropertyChangeListener {
    private PropertyChangeSupport pcs;
    private HighScore highScore;
    private HighScoreView highScoreView;

    public HighScoreScreen() {
        super();
        highScore = Factory.getHighScore();
        highScore.loadFromFile();
        highScore.addCurrentScore();
        highScoreView = new HighScoreView(highScore);
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(this);
    }

    @Override
    public void render(float deltaTime) {
        // clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        highScoreView.draw();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        highScore.saveToFile();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt.getPropertyName(), null, null);
        final String eventName = evt.getPropertyName();
    }
}


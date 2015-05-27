
package edu.chalmers.RunningMan.screens.highscoremenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.controllers.ControllerFactory;
import edu.chalmers.RunningMan.utils.highscore.HighScore;
import edu.chalmers.RunningMan.utils.highscore.HighScoreView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HighScoreScreen implements Screen, PropertyChangeListener {
    private PropertyChangeSupport pcs;
    private HighScore highScore;
    private HighScoreView highScoreView;
    private HighScoreMenuController highScoreMenuController;

    public HighScoreScreen() {
        super();
        highScore = ControllerFactory.getHighScore();
        highScore.loadFromFile();
        highScore.addCurrentScore();
        highScoreView = new HighScoreView(highScore);
        highScoreMenuController = new HighScoreMenuController(highScoreView);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        highScoreMenuController.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        highScoreMenuController.removePropertyChangeListener(this);
    }

    @Override
    public void render(float deltaTime) {
        // clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        highScoreView.draw(deltaTime);
        highScoreMenuController.update(deltaTime);
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


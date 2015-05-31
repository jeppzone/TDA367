
package edu.chalmers.RunningMan.screen.highscoremenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import edu.chalmers.RunningMan.controller.ControllerFactory;
import edu.chalmers.RunningMan.util.highscore.HighScore;
import edu.chalmers.RunningMan.util.highscore.HighScoreView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Screen for the highscore
 * @author Jesper Olsson
 */
public class HighScoreScreen implements Screen, PropertyChangeListener {
    private final PropertyChangeSupport pcs;
    private final HighScore highScore;
    private final HighScoreView highScoreView;
    private final HighScoreMenuController highScoreMenuController;

    public HighScoreScreen() {
        super();
        highScore = ControllerFactory.getHighScore();
        highScore.loadFromFile();
        highScore.addCurrentScore();
        highScoreView = new HighScoreView(highScore);
        highScoreMenuController = new HighScoreMenuController(highScoreView);
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        highScoreMenuController.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        highScoreMenuController.removePropertyChangeListener(listener);
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
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        highScore.saveToFile();
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
    }
}


package edu.chalmers.RunningMan.screens.highscoremenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chalmers.RunningMan.utils.highscore.HighScoreView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Jesper on 5/27/2015.
 */
public class HighScoreMenuController extends ClickListener {

    private HighScoreView highScoreView;
    private PropertyChangeSupport pcs;

    public HighScoreMenuController(HighScoreView highScoreView){
        this.highScoreView = highScoreView;
        pcs = new PropertyChangeSupport(this);
        highScoreView.addListener(this);
    }

    public void update(float delta) {
        Gdx.input.setInputProcessor(highScoreView);
        highScoreView.draw(delta);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        String buttonText = "";
        if (event.getTarget() instanceof Label) {
            buttonText = ((Label) event.getTarget()).getText().toString();
        }
        if (buttonText.equals("RESTART")) {
            pcs.firePropertyChange(highScoreView.getLevelName(), null, null);
        } else if (buttonText.equals("MAIN MENU")) {
            pcs.firePropertyChange("mainMenu", null, null);
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}

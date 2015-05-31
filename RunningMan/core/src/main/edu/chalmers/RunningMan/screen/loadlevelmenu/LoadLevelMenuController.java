package edu.chalmers.RunningMan.screen.loadlevelmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A controller class which controls the load level menu view and handles its input
 */
public class LoadLevelMenuController extends ClickListener {

    private final LoadLevelMenuView loadLevelMenuView;
    private final PropertyChangeSupport propertyChangeSupport;

    public LoadLevelMenuController(LoadLevelMenuView loadLevelMenuView) {
        this.loadLevelMenuView = loadLevelMenuView;
        propertyChangeSupport = new PropertyChangeSupport(this);
        loadLevelMenuView.addListener(this);
    }

    public void update(float delta) {
        // set input processor so buttons can be pressed by cursor click
        Gdx.input.setInputProcessor(loadLevelMenuView);
        // make the view render itself
        loadLevelMenuView.render(delta);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        String buttonText = "";
        // when pressing a (text) button it's the label which gets stored as target Actor in the event reference
        if (event.getTarget() instanceof Label) {
            // get the string of the button
            buttonText = ((Label) event.getTarget()).getText().toString();
        }
        // if play button is pressed, change to load level screen
        if (buttonText.equals("1")) {
            propertyChangeSupport.firePropertyChange("level1", null, null);
        } else if (buttonText.equals("2")) {
            propertyChangeSupport.firePropertyChange("level2", null, null);
        }
    }

    /**
     * Adds a Property change listener to the load level menu
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the load level menu
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}

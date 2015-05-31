package edu.chalmers.RunningMan.screen.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A controller class which controls the view and handles its input
 */
public class MainMenuController extends ClickListener {

    private final MainMenuView mainMenuView;
    private final PropertyChangeSupport propertyChangeSupport;

    public MainMenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
        propertyChangeSupport = new PropertyChangeSupport(this);
        mainMenuView.addListener(this);
    }

    public void update(float delta) {
        // set input processor so buttons can be pressed by cursor click
        Gdx.input.setInputProcessor(mainMenuView);
        // make the view render itself
        mainMenuView.render(delta);
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
        if (buttonText.equals("PLAY")) {
            propertyChangeSupport.firePropertyChange("loadScreen", null, null);
        } else if (buttonText.equals("EXIT")) {
            Gdx.app.exit();
        }
    }

    /**
     * Adds a Property change listener to the main menu
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the main menu
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}

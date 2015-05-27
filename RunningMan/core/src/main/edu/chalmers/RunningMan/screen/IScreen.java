package edu.chalmers.RunningMan.screen;

import com.badlogic.gdx.Screen;

import java.beans.PropertyChangeListener;

/**
 * An interface for menu screen
 */
public interface IScreen extends Screen {

    /**
     * Add property change listener
     * @param listener
     */
    public void	addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove property change listener
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);
}

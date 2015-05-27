package edu.chalmers.RunningMan.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.beans.PropertyChangeListener;

/**
 * Created by Kvist1 on 2015-05-21.
 */
public class MainMenuScreen implements Screen {

    private MainMenuView mainMenuView;
    private MainMenuController mainMenuController;

    public MainMenuScreen() {
        mainMenuView = new MainMenuView();
        mainMenuController = new MainMenuController(mainMenuView);
    }

    /**
     * Adds a property change listener to the controller
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mainMenuController.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the player
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mainMenuController.removePropertyChangeListener(listener);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        // clear screen
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainMenuController.update(delta);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        mainMenuView.dispose();
    }
}

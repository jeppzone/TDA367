package edu.chalmers.RunningMan.util;

import com.badlogic.gdx.Gdx;
import edu.chalmers.RunningMan.model.ISize;

/**
 * A class to get the size of the window
 */
public final class WindowSize implements ISize {

    @Override
    public float getWidth() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getHeight() {
        return Gdx.graphics.getHeight();
    }

    @Override
    public float getArea() {
        return getHeight()*getWidth();
    }
}

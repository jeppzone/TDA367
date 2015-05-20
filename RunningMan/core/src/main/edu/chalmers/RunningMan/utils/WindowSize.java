package edu.chalmers.RunningMan.utils;

import com.badlogic.gdx.Gdx;
import edu.chalmers.RunningMan.model.ISize;

/**
 * Created by Armand on 2015-05-18.
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

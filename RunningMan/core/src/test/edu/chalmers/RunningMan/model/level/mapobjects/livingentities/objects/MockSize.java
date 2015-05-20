package edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects;

import edu.chalmers.RunningMan.model.ISize;

/**
 * Created by Armand on 2015-05-20.
 */
public class MockSize implements ISize {
    @Override
    public float getWidth() {
        return 222;
    }

    @Override
    public float getHeight() {
        return 333;
    }

    @Override
    public float getArea() {
        return getHeight()*getWidth();
    }
}

package edu.chalmers.RunningMan.model;

import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;

import java.awt.*;

/**
 * Created by Jesper on 5/21/2015.
 */
public interface IPhysicalObject {

    ISize getSize();
    Position getPosition();
    Rectangle getHitbox();
}

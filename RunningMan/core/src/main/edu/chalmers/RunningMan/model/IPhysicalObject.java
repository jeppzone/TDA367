package edu.chalmers.RunningMan.model;

import java.awt.*;

/**
 * Interface for physical objects
 * @author Jesper
 */
public interface IPhysicalObject {

    ISize getSize();
    Position getPosition();
    Rectangle getHitbox();
}

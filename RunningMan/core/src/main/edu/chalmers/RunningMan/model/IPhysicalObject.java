package edu.chalmers.RunningMan.model;

import java.awt.*;

/**
 * Interface for physical gameobject
 * @author Jesper
 */
public interface IPhysicalObject {

    ISize getSize();
    Position getPosition();
    Rectangle getHitbox();
}

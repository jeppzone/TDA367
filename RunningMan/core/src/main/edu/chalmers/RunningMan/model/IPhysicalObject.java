package edu.chalmers.RunningMan.model;

import java.awt.*;

/**
 * Interface for physical gameobjects
 * @author Jesper
 */
public interface IPhysicalObject {

    ISize getSize();
    Position getPosition();
    Rectangle getHitbox();
}

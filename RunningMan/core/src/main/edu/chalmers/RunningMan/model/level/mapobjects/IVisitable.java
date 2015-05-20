package edu.chalmers.RunningMan.model.level.mapobjects;

import edu.chalmers.RunningMan.model.level.mapobjects.IVisitor;

/**
 * @author Jesper Olsson
 */
public interface IVisitable {
    void acceptVisitor(IVisitor visitor);
}

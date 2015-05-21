package edu.chalmers.RunningMan.model;

/**
 * Interface for all entities that can be collided with
 * @author Jesper Olsson
 */
public interface IVisitable {
    void acceptVisitor(IVisitor visitor);
}
